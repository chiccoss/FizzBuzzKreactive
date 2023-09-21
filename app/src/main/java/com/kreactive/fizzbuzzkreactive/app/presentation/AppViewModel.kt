package com.kreactive.fizzbuzzkreactive.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kreactive.fizzbuzzkreactive.app.domain.AppController
import com.kreactive.fizzbuzzkreactive.base.presentation.BaseViewModel

class AppViewModel(appController: AppController) : BaseViewModel(appController) {

    class Factory(private val appController: AppController) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AppViewModel(appController) as T
        }
    }
}
