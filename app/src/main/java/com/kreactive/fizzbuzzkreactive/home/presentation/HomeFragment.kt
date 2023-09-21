package com.kreactive.fizzbuzzkreactive.home.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.kreactive.fizzbuzzkreactive.app.domain.AppState
import com.kreactive.fizzbuzzkreactive.base.presentation.BaseFragment
import com.kreactive.fizzbuzzkreactive.databinding.FragmentHomeBinding
import com.kreactive.fizzbuzzkreactive.home.domain.HomeAction


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActions()
    }

    override fun render(appState: AppState) {
        super.render(appState)

        appState.homeState.error.consume()?.also {
            showGeneralError(it)
        }

        appState.homeState.validatedFizzBuzzParams.consume()?.let{
            findNavController().navigate(
                HomeFragmentDirections.goToFizzBuzzResultFragment(it)
            )
        }
    }

    private fun initActions() {
        binding.fragmentHomeSeeResultsBt.setOnClickListener {
            baseViewModel.dispatch(
                HomeAction.VerifyFizzBuzzData(
                    binding.fragmentHomeFirstWordEt.text.toString().trim(),
                    binding.fragmentHomeSecondWordEt.text.toString(),
                    binding.fragmentHomeFirstNumberEt.text.toString().trim(),
                    binding.fragmentHomeSecondNumberEt.text.toString(),
                    binding.fragmentHomeLimitEt.text.toString(),
                )
            )
        }
    }
}
