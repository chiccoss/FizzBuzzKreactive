package com.kreactive.fizzbuzzkreactive.app.domain

import com.kreactive.fizzbuzzkreactive.base.domain.ResultType


sealed class AppResult : ResultType {

    object ResetAppState : AppResult()
}
