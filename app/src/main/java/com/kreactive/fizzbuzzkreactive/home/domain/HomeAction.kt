package com.kreactive.fizzbuzzkreactive.home.domain

import com.kreactive.fizzbuzzkreactive.base.domain.ActionType
import com.kreactive.fizzbuzzkreactive.home.data.FizzBuzzData


sealed class HomeAction : ActionType {
    object InitApplication:  HomeAction()
    data class VerifyFizzBuzzData(
        val firstWord: String,
        val secondWord: String,
        val firstNumber: String,
        val secondNumber: String,
        val limit: String
    ) : HomeAction()

    data class GetFizzBuzzResult(val fizzBuzzData: FizzBuzzData) : HomeAction()

}

