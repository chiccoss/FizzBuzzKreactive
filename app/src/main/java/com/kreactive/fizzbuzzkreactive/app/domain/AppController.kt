package com.kreactive.fizzbuzzkreactive.app.domain

import androidx.lifecycle.MutableLiveData
import com.kreactive.fizzbuzzkreactive.base.domain.ActionType
import com.kreactive.fizzbuzzkreactive.base.domain.ResultType

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.Executors

class AppController(
    private val processor: AppProcessor,
    private val reducer: AppReducer,
    initialState: AppState
) : CoroutineScope by CoroutineScope(Executors.newSingleThreadExecutor().asCoroutineDispatcher()) {


    val state = MutableLiveData(initialState)

    private val results = MutableSharedFlow<ResultType>()

    init {
        launch {
            results.collect { result ->
                val v = state.value ?: return@collect
                val newState = reducer.reduce(result, v)
                withContext(Dispatchers.Main) {
                    state.value = newState
                }
            }
        }
    }

    fun dispatch(action: ActionType) {
        launch {
            processor.process(action) { result ->
                launch {
                    results.emit(result)
                }
            }
        }
    }
}
