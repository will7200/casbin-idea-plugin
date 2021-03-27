package io.github.will7200.plugins.casbin.executor

import com.github.difflib.DiffUtils
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import io.github.will7200.plugins.casbin.*
import io.github.will7200.plugins.casbincsv.language.psi.CasbinCSVPsiFile
import kotlinx.coroutines.*
import kotlin.time.ExperimentalTime

class CasbinEditorTreeDiff(private val project: Project) : CasbinDocumentProducer {
    private val log: Logger = Logger.getInstance(this::class.java)
    private var contentAddedJob: Job? = null
    private var lines: MutableList<String> = mutableListOf()
    private val casbinService: CasbinExecutorService by lazy {
        project.service<CasbinExecutorService>()
    }
    private var oldContent: String? = null

    init {
        val connection = project.messageBus.connect()
        connection.subscribe(CasbinTopics.DOCUMENT_REQUEST_TOPIC, this)
    }

    @ExperimentalTime
    override fun processChange(change: CasbinDocumentRequest) {
        project.messageBus.syncPublisher(CasbinTopics.DOCUMENT_RESPONSE_TOPIC)
            .beforeProcessing(change)
        casbinService.createEnforcer(
            CasbinExecutorRequest.CasbinCreateEnforcer(
                change.toolWindow.modelDefinitionFileText,
                change.toolWindow.policyFileText
            )
        )
        when (change) {
            is CasbinDocumentRequest.ExecuteEntireDocument -> {
                contentAddedJob?.cancel("Processing entire document instead")
                process(change)
            }
            is CasbinDocumentRequest.ContentAdded -> {
                contentAddedJob?.cancel()
                contentAddedJob = GlobalScope.launch {
                    delay(500)
                    withContext(Dispatchers.Unconfined) {
                        onNewContent(change)
                    }
                }
            }
            is CasbinDocumentRequest.ContentRemoved -> {
                oldContent?.let {
                    val patch = DiffUtils.diff(oldContent!!.lines(), change.document.text.lines())
                    change.patch = patch
                }
            }
            else -> {
                TODO("Handle class ${change.toString()}")
            }
        }
        oldContent = change.document.text
        project.messageBus.syncPublisher(CasbinTopics.DOCUMENT_RESPONSE_TOPIC)
            .afterProcessing(change)
    }

    private fun process(change: CasbinDocumentRequest.ExecuteEntireDocument) {
        val document = change.document
        runReadAction {
            val psiFile =
                PsiDocumentManager.getInstance(project).getPsiFile(document)
                    ?: return@runReadAction
            val casbinCSVFile = if (psiFile is CasbinCSVPsiFile) psiFile else return@runReadAction
            for (record in casbinCSVFile.records) {
                val ln = document.getLineNumber(record.textOffset)
                casbinService.executeEnforcement(
                    CasbinExecutorRequest.CasbinEnforcementRequest(
                        change.toolWindow.modelDefinitionFileText,
                        change.toolWindow.policyFileText,
                        record.fieldList.map { it.text }.toTypedArray()
                    ).apply {
                        this.model = change.editor.editor?.markupModel
                        this.lineNumber = ln
                    }
                )
            }
        }
    }

    private fun onNewContent(change: CasbinDocumentRequest.ContentAdded) {
        val document = change.document
        runReadAction {
            val psiFile =
                PsiDocumentManager.getInstance(project).getPsiFile(document)
                    ?: return@runReadAction
            val casbinCSVFile = if (psiFile is CasbinCSVPsiFile) psiFile else return@runReadAction
            for (record in casbinCSVFile.records) {
                val ln = document.getLineNumber(record.textOffset)
                lines.add(ln, record.text)
                casbinService.executeEnforcement(
                    CasbinExecutorRequest.CasbinEnforcementRequest(
                        change.toolWindow.modelDefinitionFileText,
                        change.toolWindow.policyFileText,
                        record.fieldList.map { it.text }.toTypedArray()
                    ).apply {
                        this.model = change.editor.editor?.markupModel
                        this.lineNumber = ln
                    }
                )
            }
        }
    }
}