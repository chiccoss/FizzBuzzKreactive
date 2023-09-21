package com.kreactive.fizzbuzzkreactive.app.domain

import com.kreactive.fizzbuzzkreactive.base.domain.ReducerType
import com.kreactive.fizzbuzzkreactive.base.domain.ResultType
import com.kreactive.fizzbuzzkreactive.home.domain.HomeReducer

class AppReducer(
    private val initialState: AppState,
    private val homeReducer: HomeReducer
) : ReducerType<AppState> {

    override fun reduce(result: ResultType, state: AppState): AppState {

        return when (result) {
            is AppResult.ResetAppState -> initialState

            else -> AppState(
                homeState = homeReducer.reduce(result, state.homeState),
            )
        }
    }
}
