package com.kreactive.fizzbuzzkreactive.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.kreactive.fizzbuzzkreactive.app.extention.showShortSnackbar
import com.kreactive.fizzbuzzkreactive.app.presentation.AppViewModel
import com.kreactive.fizzbuzzkreactive.app.domain.AppState
import com.kreactive.fizzbuzzkreactive.base.error.ErrorInterface
import com.kreactive.fizzbuzzkreactive.base.error.AppErrorType
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected val baseViewModel: AppViewModel by inject { parametersOf(this) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = createBindingInstance(inflater, container).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel.state.observe(viewLifecycleOwner, ::render)
    }

    protected open fun render(appState: AppState) {
        appState.homeState.error.consume()?.also {
            showGeneralError(it)
        }
    }

    fun showGeneralError(error :ErrorInterface) {
        if(error is AppErrorType)
            showShortSnackbar(error.name)
    }


    private fun createBindingInstance(inflater: LayoutInflater, container: ViewGroup?): VB {
        val vbType = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        val vbClass = vbType as Class<VB>
        val method = vbClass.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        return method.invoke(null, inflater, container, false) as VB
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
