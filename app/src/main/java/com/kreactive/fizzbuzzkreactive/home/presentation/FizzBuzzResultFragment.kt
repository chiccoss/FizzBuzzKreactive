package com.kreactive.fizzbuzzkreactive.home.presentation

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.kreactive.fizzbuzzkreactive.R
import com.kreactive.fizzbuzzkreactive.app.domain.AppState
import com.kreactive.fizzbuzzkreactive.base.presentation.BaseFragment
import com.kreactive.fizzbuzzkreactive.databinding.FragmentFizzBuzzResultBinding
import com.kreactive.fizzbuzzkreactive.home.domain.HomeAction


class FizzBuzzResultFragment : BaseFragment<FragmentFizzBuzzResultBinding>() {

    private val args: FizzBuzzResultFragmentArgs by navArgs()
    private val fizzBuzzAdapter = FizzBuzzAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        baseViewModel.dispatch(HomeAction.GetFizzBuzzResult(args.fizzBuzzResult))
    }

    override fun render(appState: AppState) {
        super.render(appState)
        appState.homeState.fizzBuzzDataResultEvent.consume()?.let {
            fizzBuzzAdapter.submitList(it)
        }
    }

    private fun initView() {
        binding.fragmentFizzBuzzResultRv.adapter = fizzBuzzAdapter

        binding.fragmentFizzBuzzResultFirstWordTv.text = getString(R.string.fragment_results_first_word_text, args.fizzBuzzResult.firstWord)
        binding.fragmentFizzBuzzResultSecondWordTv.text = getString(R.string.fragment_results_second_word_text, args.fizzBuzzResult.secondWord)
        binding.fragmentFizzBuzzResultFirstNumberTv.text = getString(R.string.fragment_results_first_number_text, args.fizzBuzzResult.firstNumber)
        binding.fragmentFizzBuzzResultSecondNumberTv.text = getString(R.string.fragment_results_second_number_text, args.fizzBuzzResult.secondNumber)
        binding.fragmentFizzBuzzResultLimitTv.text = getString(R.string.fragment_results_limit_text, args.fizzBuzzResult.limit)
    }

}
