package com.kreactive.fizzbuzzkreactive.app.domain



import com.kreactive.fizzbuzzkreactive.base.domain.StateType

import com.kreactive.fizzbuzzkreactive.home.domain.HomeState

data class AppState(
    val homeState: HomeState = HomeState(),
) : StateType
