package com.kreactive.fizzbuzzkreactive.home.domain

import com.kreactive.fizzbuzzkreactive.base.domain.ReducerType
import com.kreactive.fizzbuzzkreactive.base.domain.ResultType
import com.kreactive.fizzbuzzkreactive.base.util.BooleanOneTimeEvent
import com.kreactive.fizzbuzzkreactive.base.util.OneTimeEvent

class HomeReducer : ReducerType<HomeState> {
    override fun reduce(result: ResultType, state: HomeState): HomeState {
        return when (result) {
            is HomeResult.IsLoading -> state.copy(
                isLoading = true
            )
            is HomeResult.AppError -> state.copy(
                isLoading = false,
                error = OneTimeEvent(result.error)
            )
            is HomeResult.InitApplication -> state.copy(
                initApplicationResultEvent = BooleanOneTimeEvent(true),
                isLoading = false
            )
            is HomeResult.GotFizzBuzzResult -> state.copy(
                fizzBuzzDataResultEvent = OneTimeEvent(result.fizzBuzzResult),
                isLoading = false
            )
            is HomeResult.VerifiedFizzBuzzData -> state.copy(
                validatedFizzBuzzParams = OneTimeEvent(result.fizzBuzzData),
                isLoading = false
            )
            else -> state
        }
    }

}
