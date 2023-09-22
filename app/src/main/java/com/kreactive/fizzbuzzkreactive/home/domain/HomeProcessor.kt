package com.kreactive.fizzbuzzkreactive.home.domain

import android.content.Context
import com.kreactive.fizzbuzzkreactive.base.domain.ActionType
import com.kreactive.fizzbuzzkreactive.base.domain.ProcessorResultCallback
import com.kreactive.fizzbuzzkreactive.base.domain.ProcessorType
import com.kreactive.fizzbuzzkreactive.home.data.HomeRepository
import com.kreactive.fizzbuzzkreactive.app.Resource
import com.kreactive.fizzbuzzkreactive.app.extention.isCastableToInt
import com.kreactive.fizzbuzzkreactive.base.error.AppErrorType
import com.kreactive.fizzbuzzkreactive.home.data.FizzBuzzData
import kotlinx.coroutines.delay

class HomeProcessor(
    private val context: Context,
    private val homeRepository: HomeRepository,
) : ProcessorType {

    override suspend fun process(action: ActionType, next: ProcessorResultCallback) {
        (action as? HomeAction)?.also { processorAction ->
            when (processorAction) {
                HomeAction.InitApplication -> initApplication(next)
                is HomeAction.VerifyFizzBuzzData -> verifyFizzBuzzData(
                    processorAction.firstWord,
                    processorAction.secondWord,
                    processorAction.firstNumber,
                    processorAction.secondNumber,
                    processorAction.limit,
                    next
                )

                is HomeAction.GetFizzBuzzResult -> getFizzBuzzResult(
                    processorAction.fizzBuzzData,
                    next
                )
            }
        }
    }

    private fun verifyFizzBuzzData(
        firstWord: String,
        secondWord: String,
        firstNumber: String,
        secondNumber: String,
        limit: String,
        next: ProcessorResultCallback
    ) {
        next(HomeResult.IsLoading)
        if (firstWord.isBlank()) return next(HomeResult.AppError(AppErrorType.EMPTY_TEXT_1))
        if (secondWord.isBlank()) return next(HomeResult.AppError(AppErrorType.EMPTY_TEXT_2))

        if (firstNumber.isBlank()) return next(HomeResult.AppError(AppErrorType.EMPTY_NUMBER_1))
        if (!firstNumber.isCastableToInt()) return next(HomeResult.AppError(AppErrorType.INVALID_NUMBER_1))

        if (secondNumber.isBlank() ) return next(HomeResult.AppError(AppErrorType.EMPTY_NUMBER_2))
        if (!secondNumber.isCastableToInt()) return next(HomeResult.AppError(AppErrorType.INVALID_NUMBER_2))

        if (limit.isBlank() ) return next(HomeResult.AppError(AppErrorType.EMPTY_LIMIT))
        if (!limit.isCastableToInt()) return next(HomeResult.AppError(AppErrorType.INVALID_LIMIT))


        val fizzBuzzData = FizzBuzzData(firstWord, secondWord, firstNumber, secondNumber, limit)
        next(HomeResult.VerifiedFizzBuzzData(fizzBuzzData))
    }

    private suspend fun getFizzBuzzResult(
        fizzBuzzData: FizzBuzzData,
        next: ProcessorResultCallback
    ) {
        next(HomeResult.IsLoading)
        homeRepository.getFizzBuzzResult(
            fizzBuzzData.firstWord,
            fizzBuzzData.secondWord,
            fizzBuzzData.firstNumber.toInt(),
            fizzBuzzData.secondNumber.toInt(),
            fizzBuzzData.limit.toInt()
        ).apply {
            when (this) {
                is Resource.Success -> next(HomeResult.GotFizzBuzzResult(data))
                is Resource.Error -> error.message?.let {  next(HomeResult.Error(it))}
                is Resource.AppError -> next(HomeResult.AppError(error as AppErrorType))
            }
        }
    }

    private suspend fun initApplication(next: ProcessorResultCallback) {
        delay(1000) //TODO SIMULATE APP INIT
        next(HomeResult.InitApplication)
    }


}
