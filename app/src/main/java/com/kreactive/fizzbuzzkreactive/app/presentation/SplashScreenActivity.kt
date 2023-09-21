package com.kreactive.fizzbuzzkreactive.app.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kreactive.fizzbuzzkreactive.app.domain.AppState
import com.kreactive.fizzbuzzkreactive.base.presentation.BaseActivity
import com.kreactive.fizzbuzzkreactive.databinding.ActivitySplashScreenBinding
import com.kreactive.fizzbuzzkreactive.home.domain.HomeAction
import com.kreactive.fizzbuzzkreactive.home.presentation.HomeActivity


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { true }
        baseViewModel.dispatch(HomeAction.InitApplication)
    }

    override fun render(appState: AppState) {
        super.render(appState)
        if(appState.homeState.initApplicationResultEvent.consume()) {
            goToHome()
        }
    }


    private fun goToHome() {
        HomeActivity.start(this)
        finish()
    }


}
