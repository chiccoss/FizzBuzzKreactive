package com.kreactive.fizzbuzzkreactive.home.presentation


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.kreactive.fizzbuzzkreactive.app.domain.AppState
import com.kreactive.fizzbuzzkreactive.base.presentation.BaseActivity
import com.kreactive.fizzbuzzkreactive.databinding.ActivityHomeBinding
import com.kreactive.fizzbuzzkreactive.home.domain.HomeAction
import org.koin.core.component.KoinComponent

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityHomeBinding.inflate(layoutInflater)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun render(appState: AppState) {
        super.render(appState)

        binding.activityHomeLoader.isVisible = appState.homeState.isLoading

    }

    companion object {
        fun start(context: Context,) {
            val intent = Intent(context, HomeActivity::class.java)
            ContextCompat.startActivity(context, intent, null)
        }
    }

}