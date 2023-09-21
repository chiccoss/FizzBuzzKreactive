package com.kreactive.fizzbuzzkreactive.app.domain

import android.content.Context
import com.kreactive.fizzbuzzkreactive.base.domain.ActionType
import com.kreactive.fizzbuzzkreactive.base.domain.ProcessorResultCallback
import com.kreactive.fizzbuzzkreactive.base.domain.ProcessorType

class AppProcessor(
    private val processors: List<ProcessorType>,
    private val context: Context
) : ProcessorType {

    override suspend fun process(action: ActionType, next: ProcessorResultCallback) {
        for (processor in processors) {
            processor.process(action, next)
        }
    }
}
