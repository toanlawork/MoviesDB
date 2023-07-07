package com.example.moviesdb.client.auth

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.moviesdb.ApiResponse
import com.example.moviesdb.MovieApplication
import com.example.moviesdb.R
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

/**
 * This class has handle token unauthorized
 *
 */
class RequestInterceptor @Inject constructor(@ApplicationContext val context: Context) :
    Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val connectionListener = MovieApplication.getInternetConnectionListener()
        if (!context.isConnectedToNetwork()) {
            // Handle network error
            connectionListener.onInternetUnavailable()
            // Throwing our custom exception 'NoConnectivityException'
            throw NoConnectivityException()
        }
        // 1. sign this request
        val request: Request = chain.request()

        // 2. proceed with the request
        val response: Response = chain.proceed(request)

        val responseCode = response.code
        Log.d("RequestInterceptor - response code", "$responseCode")
        // check if still unauthorized (i.e. refresh failed)
        if (ApiResponse.HTTP_UNAUTHORIZED == responseCode) {
            // clean your access token and prompt user for login again.
            Log.d("RequestInterceptor - token unauthorized", "token unauthorized")
            // Handle network error
            connectionListener.tokenExpired()
        } else if (ApiResponse.HTTP_FORBIDDEN == responseCode) {
            // access denied
            connectionListener.accessDenied()
        }
        // returning the response to the original request
        return response
    }
}


fun Context.isConnectedToNetwork(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    /*return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false*/
    val networkCapabilities =
        connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)
    return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
}


class NoConnectivityException : IOException() {

    fun getMessage(context: Context): String {
        return context.getString(R.string.network_disconnect)
        // You can send any message whatever you want from here.
    }
}
