
package com.kreactive.fizzbuzzkreactive.app.extention

fun String.isCastableToInt(): Boolean {
    return when (toIntOrNull()) {
        null -> false
        else -> true
    }
}
