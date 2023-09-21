package com.kreactive.fizzbuzzkreactive.app.injection

import com.kreactive.fizzbuzzkreactive.app.domain.AppController
import com.kreactive.fizzbuzzkreactive.app.domain.AppProcessor
import com.kreactive.fizzbuzzkreactive.app.domain.AppReducer
import com.kreactive.fizzbuzzkreactive.app.domain.AppState
import com.kreactive.fizzbuzzkreactive.app.presentation.AppViewModel
import com.kreactive.fizzbuzzkreactive.home.domain.HomeProcessor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    single { AppController(get(), get(), get()) }

    single { AppProcessor(get(), get()) }

    single { AppState() }

    viewModel { AppViewModel(get()) }

    // region Processors

    single {
        listOf(
            get<HomeProcessor>()
        )
    }

    // endregion Processors

    // region Reducers

    single {
        AppReducer(
            initialState = get(),
            homeReducer = get()
        )
    }

    // endregion Reducers
}
