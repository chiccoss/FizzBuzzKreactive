package com.kreactive.fizzbuzzkreactive.base.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kreactive.fizzbuzzkreactive.app.domain.AppController
import com.kreactive.fizzbuzzkreactive.app.domain.AppState
import com.kreactive.fizzbuzzkreactive.base.domain.ActionType

abstract class BaseViewModel(private val appController: AppController) : ViewModel() {

    val state: LiveData<AppState> = appController.state

    val lastState: AppState
        get() = state.value ?: AppState()

    fun dispatch(action: ActionType) {
        appController.dispatch(action)
    }
}
