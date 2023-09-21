package com.kreactive.fizzbuzzkreactive.app

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.kreactive.fizzbuzzkreactive.base.error.ErrorInterface
import com.kreactive.fizzbuzzkreactive.base.error.AppErrorType

open class ResponseHandler(private val context: Context) {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.Success(data)
    }

    fun handleException(e: Any): Resource<Nothing> {
        return when (e) {
            is Exception -> {
                Resource.Error(e)
            }
            is AppErrorType -> {
                Resource.AppError(e)
            }
            else -> {
                Resource.Error(Exception("Unknown error occurred"))
            }
        }
    }

    private fun isConnecting(): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.let {
            it.getNetworkCapabilities(it.activeNetwork)?.let { networkCapabilities ->
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                )
            } ?: false
        } ?: false
    }
}

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val error: Exception) : Resource<Nothing>()
    data class AppError(val error: ErrorInterface) : Resource<Nothing>()
}
