package com.kreactive.fizzbuzzkreactive.home.injection

import com.kreactive.fizzbuzzkreactive.home.data.HomeRepository
import com.kreactive.fizzbuzzkreactive.home.domain.HomeProcessor
import com.kreactive.fizzbuzzkreactive.home.domain.HomeReducer
import org.koin.dsl.module

val HomeModule = module {
    single { HomeRepository(get()) }
    single { HomeProcessor(get(),get()) }
    single { HomeReducer() }
}
