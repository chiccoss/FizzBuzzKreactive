package com.kreactive.fizzbuzzkreactive.home.domain

import com.kreactive.fizzbuzzkreactive.base.domain.ResultType
import com.kreactive.fizzbuzzkreactive.base.error.AppErrorType
import com.kreactive.fizzbuzzkreactive.home.data.FizzBuzzData


sealed class HomeResult : ResultType {
    object IsLoading : HomeResult()
    object InitApplication : HomeResult()
    data class AppError(val error : AppErrorType) : HomeResult()
    data class Error(val error : String) : HomeResult()
    class GotFizzBuzzResult(val fizzBuzzResult: List<String>) : HomeResult()
    data class VerifiedFizzBuzzData(val fizzBuzzData: FizzBuzzData): HomeResult()
}
