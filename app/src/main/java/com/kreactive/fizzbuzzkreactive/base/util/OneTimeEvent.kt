package com.kreactive.fizzbuzzkreactive.base.util

import java.util.concurrent.atomic.AtomicBoolean

open class OneTimeEvent<T>(private val data: T) {

    private val pending = AtomicBoolean(true)

    open fun consume(): T? {
        if (pending.compareAndSet(true, false)) {
            return data
        }
        return null
    }

    fun peek(): T {
        return data
    }
}
