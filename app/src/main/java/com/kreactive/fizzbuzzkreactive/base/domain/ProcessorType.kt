package com.kreactive.fizzbuzzkreactive.base.domain

typealias ProcessorResultCallback = (ResultType) -> Unit

interface ProcessorType {

    suspend fun process(action: ActionType, next: ProcessorResultCallback)
}
