package com.kreactive.fizzbuzzkreactive.home.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.kreactive.fizzbuzzkreactive.app.Resource
import com.kreactive.fizzbuzzkreactive.app.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import timber.log.Timber


typealias AsyncCallback<T> = (Resource<T>) -> Unit

class HomeRepository(
    private val context: Context,
) : ResponseHandler(context) {

    //region Network Connection
    suspend fun isNetworkAvailable(): Flow<Boolean> = callbackFlow {

        try {
            if (!isConnecting()) {
                send(false)
            }

            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val request = NetworkRequest.Builder()
            request.addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onUnavailable() {
                    super.onUnavailable()
                    trySend(false)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(true)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(false)
                }
            }

            connectivityManager.requestNetwork(request.build(), callback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        } catch (error: Exception) {
            Timber.e(error)
            send(true)
        }
        awaitClose()
    }

    private fun isConnecting(): Boolean {
        return (context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager)?.let { connectivtyManager ->
            connectivtyManager.getNetworkCapabilities(connectivtyManager.activeNetwork)?.let {
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || it.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                )
            } ?: false
        } ?: false
    }

    suspend fun getFizzBuzzResult(
        firstWord: String,
        secondWord: String,
        firstNumber: Int,
        secondNumber: Int,
        limit: Int,
    ): Resource<List<String>> = withContext(Dispatchers.IO) {
        try {
            delay(2000) //SIMULATE NETWORK CALL
            val listString = mutableListOf<String>()
            var value = ""
            repeat(limit) { i ->
                value = ""
                val index = i + 1
                if (index % firstNumber == 0) value += firstWord
                if (index % secondNumber == 0) value += secondWord
                if (value.isEmpty()) value = index.toString()
                listString.add("$index : $value")
            }
            handleSuccess(listString)
        } catch (e : Exception){
            handleException(e)
        }
    }
    //endregion


}