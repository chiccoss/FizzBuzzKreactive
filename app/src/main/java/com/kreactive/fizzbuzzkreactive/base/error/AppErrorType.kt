package com.kreactive.fizzbuzzkreactive.base.error

import com.kreactive.fizzbuzzkreactive.R
import com.kreactive.fizzbuzzkreactive.base.error.ErrorInterface

enum class AppErrorType(val message: Int) : ErrorInterface {

    EMPTY_TEXT_1(R.string.empty_first_word),
    EMPTY_TEXT_2(R.string.empty_second_word),
    EMPTY_NUMBER_1(R.string.empty_first_number),
    EMPTY_NUMBER_2(R.string.empty_second_number),
    EMPTY_LIMIT(R.string.empty_limit),
    INVALID_NUMBER_1(R.string.invalid_first_number),
    INVALID_NUMBER_2(R.string.invalid_second_number),
    INVALID_LIMIT(R.string.invalid_limit),

}
