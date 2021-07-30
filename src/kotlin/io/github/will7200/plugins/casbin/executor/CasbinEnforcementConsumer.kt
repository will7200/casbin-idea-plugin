package io.github.will7200.plugins.casbin.executor

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.markup.MarkupModel
import com.intellij.openapi.editor.markup.RangeHighlighter
import com.intellij.openapi.project.Project
import com.jetbrains.rd.util.concurrentMapOf
import io.github.will7200.plugins.casbin.*
import io.github.will7200.plugins.casbin.view.ui.CasbinRequestGutterIconRenderer
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

class CasbinEnforcementConsumer(project: Project) : CasbinExecutorConsumer, CasbinDocumentConsumer {
    private val removeChannel = Channel<RangeHighlighter>(100)
    private val requestChannel = Channel<CasbinExecutorRequest.CasbinEnforcementRequest>(100)
    private var addHighlightsJob: Job? = null
    private var removeHighlightsJob: Job? = null

    init {
        val connection = project.messageBus.connect()
        connection.subscribe(CasbinTopics.DOCUMENT_RESPONSE_TOPIC, this)
        connection.subscribe(CasbinTopics.EXECUTOR_RESPONSE_TOPIC, this)
    }

    private val log: Logger = Logger.getInstance(this::class.java)
    private val highlights = concurrentMapOf<Int, RangeHighlighter>()

    override fun beforeProcessing(request: CasbinDocumentRequest) {
        when (request) {
            is CasbinDocumentRequest.ExecuteEntireDocument -> {
                removeHighlightsJob?.cancel("removing all highlights")
                highlights.clear()
                removeHighlights(request.toolWindow.requestEditorPane.editor!!.markupModel, null)
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun afterProcessing(request: CasbinDocumentRequest) {
        when (request) {
            is CasbinDocumentRequest.ContentRemoved -> {
                for (delta in request.patch?.deltas!!) {
                    val rh = CasbinDocumentRequest.RemoveHighlighter(
                        delta.source.position,
                        delta.source.position + 1,
                        request.document,
                        request.toolWindow
                    ).apply {
                        model = request.editor.editor?.markupModel
                    }
                    process(rh)
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    private fun process(request: CasbinDocumentRequest.RemoveHighlighter) {
        val range = request.range()
        highlights.keys.filter {
            it >= range.first && it < range.second
        }.map {
            val highlighter = highlights[it]!!
            highlights.remove(it)
            GlobalScope.launch {
                removeChannel.send(highlighter)
                removeHighlightsJob?.cancel()
                removeHighlightsJob = GlobalScope.launch {
                    delay(250)
                    withContext(Dispatchers.Unconfined) {
                        val removeHighlights = mutableListOf<RangeHighlighter>()
                        while (!removeChannel.isEmpty) {
                            removeHighlights.add(removeChannel.receive())
                        }
                        removeHighlights(request.model!!, removeHighlights)
                    }
                }
            }
        }
    }

    override fun beforeProcessing(request: CasbinExecutorRequest) {
        when (request) {
            is CasbinExecutorRequest.CasbinEnforcementRequest -> {
                // DO NOTHING
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun afterProcessing(request: CasbinExecutorRequest) {
        when (request) {
            is CasbinExecutorRequest.CasbinEnforcementRequest -> process(request)
            is CasbinExecutorRequest.CasbinFileChangeNotify -> {
                if (request.enforcerSwapped) {
                    removeHighlightsJob?.cancel("removing all highlights")
                    highlights.clear()
                }
            }
            else -> {
                TODO("Not yet implemented $request")
            }
        }
    }

    private fun removeHighlights(
        model: MarkupModel,
        removeHighlights: List<RangeHighlighter>?
    ) {
        ApplicationManager.getApplication().invokeAndWait(Runnable {
            ApplicationManager.getApplication().runWriteAction {
                if (removeHighlights.isNullOrEmpty()) {
                    model.removeAllHighlighters()
                } else {
                    for (remove in removeHighlights) {
                        log.warn("removing ${remove}")
                        model.removeHighlighter(remove)
                    }
                }
            }
        })
    }

    private fun addHighlights(
        addHighlights: List<CasbinExecutorRequest.CasbinEnforcementRequest>
    ) {
        if (addHighlights.isNotEmpty()) {
            if (addHighlights[0].model?.document?.lineCount == 0) {
                return
            }
        }
        ApplicationManager.getApplication().invokeAndWait(Runnable {
            ApplicationManager.getApplication().runWriteAction {
                for (request in addHighlights) {
                    val highlighter = highlights.getOrPut(
                        request.lineNumber!!, {
                            request.model!!.addLineHighlighter(
                                request.lineNumber!!,
                                0,
                                null
                            )
                        }
                    )
                    highlighter.gutterIconRenderer =
                        CasbinRequestGutterIconRenderer(request)
                }
            }
        })
    }

    @ExperimentalCoroutinesApi
    private fun process(request: CasbinExecutorRequest.CasbinEnforcementRequest) {
        request.lineNumber?.let {
            GlobalScope.launch {
                requestChannel.send(request)
                addHighlightsJob?.cancel()
                addHighlightsJob = GlobalScope.launch {
                    delay(250)
                    withContext(Dispatchers.Unconfined) {
                        val addHighlights = mutableListOf<CasbinExecutorRequest.CasbinEnforcementRequest>()
                        while (!requestChannel.isEmpty) {
                            addHighlights.add(requestChannel.receive())
                        }
                        addHighlights(addHighlights)
                    }
                }
            }
        }
    }

}