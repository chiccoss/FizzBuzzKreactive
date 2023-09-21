package com.kreactive.fizzbuzzkreactive.home.domain


import com.kreactive.fizzbuzzkreactive.base.domain.StateType
import com.kreactive.fizzbuzzkreactive.base.error.ErrorInterface
import com.kreactive.fizzbuzzkreactive.base.util.BooleanOneTimeEvent
import com.kreactive.fizzbuzzkreactive.base.util.OneTimeEvent
import com.kreactive.fizzbuzzkreactive.home.data.FizzBuzzData

data class HomeState(
    val isLoading: Boolean = false,
    val string: OneTimeEvent<String?> = OneTimeEvent(null),
    val gotNetworkConnectionResultEvent: BooleanOneTimeEvent = BooleanOneTimeEvent(false),
    val hasNetworkConnection: Boolean = true,
    val initApplicationResultEvent: BooleanOneTimeEvent = BooleanOneTimeEvent(false),
    val error: OneTimeEvent<ErrorInterface?> = OneTimeEvent(null),
    val validatedFizzBuzzParams : OneTimeEvent<FizzBuzzData?> = OneTimeEvent(null),
    val fizzBuzzDataResultEvent : OneTimeEvent<List<String>?> = OneTimeEvent(null)
) : StateType
