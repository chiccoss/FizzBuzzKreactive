package com.kreactive.fizzbuzzkreactive.base.util

class BooleanOneTimeEvent(value: Boolean = true) : OneTimeEvent<Boolean>(value) {

    override fun consume(): Boolean {
        return super.consume() ?: false
    }
}
