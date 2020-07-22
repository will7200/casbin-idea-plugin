package io.github.will7200.plugins.casbin.executor

import com.github.difflib.DiffUtils
import com.intellij.openapi.application.runReadAction
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiTreeChangeEvent
import com.intellij.psi.PsiTreeChangeListener
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
        when (change) {
            is CasbinDocumentRequest.ContentAdded -> {
                contentAddedJob?.cancel()
                contentAddedJob = GlobalScope.launch {
                    delay(500)
                    withContext(Dispatchers.Unconfined) {
                        onNewContent(change)
                    }
                }
                oldContent = change.document.text
            }
            is CasbinDocumentRequest.ContentRemoved -> {
                oldContent?.let {
                    val patch = DiffUtils.diff(oldContent!!.lines(), change.document.text.lines())
                    for (delta in patch.deltas) {
                        project.messageBus.syncPublisher(CasbinTopics.DOCUMENT_RESPONSE_TOPIC)
                            .beforeProcessing(
                                CasbinDocumentRequest.RemoveHighlighter(
                                    delta.source.position,
                                    delta.source.position + 1,
                                    change.document
                                ).apply {
                                    model = change.editor.editor?.markupModel
                                })
                    }
                }
                oldContent = change.document.text
            }
            else -> {
                TODO("Handle class ${change.toString()}")
            }
        }
    }

    private fun onNewContent(change: CasbinDocumentRequest.ContentAdded) {
        val document = change.document
        var casbinCSVFile: CasbinCSVPsiFile?
        runReadAction {
            val psiFile =
                PsiDocumentManager.getInstance(ProjectManager.getInstance().defaultProject).getPsiFile(document)
                    ?: return@runReadAction
            casbinCSVFile = if (psiFile is CasbinCSVPsiFile) psiFile else return@runReadAction
            for (record in casbinCSVFile!!.records) {
                val ln = document.getLineNumber(record.textOffset)
                log.warn("processing $ln")
                if (record.text.isEmpty()) {
                    log.warn("processing $ln: empty continuing")
                    continue
                }
//                try {
//                    if (lines[ln] == record.text) {
//                        log.warn("processing $ln: cached equals")
//                        continue
//                    }
//                } catch (ex: IndexOutOfBoundsException) {
//
//                }
                lines.add(ln, record.text)
                casbinService.executeEnforcement(
                    CasbinExecutorRequest.CasbinEnforcementRequest(
                        change.toolWindow.modelDefinitionFile,
                        change.toolWindow.policyFile,
                        record.fieldList.map { it.text }.toTypedArray()
                    ).apply {
                        this.model = change.editor.editor?.markupModel
                        this.lineNumber = ln
                    }
                )
            }
        }
    }

    class DiffTree(document: Document) : PsiTreeChangeListener {
        override fun beforePropertyChange(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

        override fun childReplaced(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

        override fun childrenChanged(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

        override fun beforeChildAddition(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

        override fun beforeChildReplacement(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

        override fun propertyChanged(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

        override fun beforeChildrenChange(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

        override fun childMoved(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

        override fun childRemoved(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

        override fun beforeChildMovement(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

        override fun childAdded(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

        override fun beforeChildRemoval(event: PsiTreeChangeEvent) {
            TODO("Not yet implemented")
        }

    }
}