package com.kreactive.fizzbuzzkreactive.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.kreactive.fizzbuzzkreactive.app.extention.showShortToast
import com.kreactive.fizzbuzzkreactive.app.presentation.AppViewModel
import com.kreactive.fizzbuzzkreactive.app.domain.AppState
import com.kreactive.fizzbuzzkreactive.base.error.ErrorInterface
import com.kreactive.fizzbuzzkreactive.base.error.AppErrorType
import com.kreactive.fizzbuzzkreactive.home.domain.HomeAction

import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected val baseViewModel: AppViewModel by inject { parametersOf(this) }


    abstract fun inflateLayout(layoutInflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateLayout(layoutInflater)
        setContentView(binding.root)
        baseViewModel.state.observe(this, ::render)
    }

    protected open fun render(appState: AppState) {

        appState.homeState.error.consume()?.also { error ->
            showGeneralError(error)
        }
    }

    private fun showGeneralError(error: ErrorInterface) {
        if(error is AppErrorType){
            showShortToast(error.message)
        }
    }
}
