package com.example.moviesdb

import android.app.Application
import com.example.moviesdb.client.auth.ConnectionListener
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MovieApplication : Application() {

    override fun onTerminate() {
        super.onTerminate()
        removeInternetConnectionListener()
    }

    companion object {
        private val sRequestConnectionListener: MutableList<ConnectionListener> = ArrayList()

        fun setInternetConnectionListener(mInternetConnectionListener: ConnectionListener?) {
            mInternetConnectionListener?.let {
                sRequestConnectionListener.add(
                    Constant.INT_ZERO,
                    it,
                )
            }
        }

        private fun removeInternetConnectionListener() {
            sRequestConnectionListener.clear()
        }

        fun getInternetConnectionListener(): ConnectionListener {
            return sRequestConnectionListener[Constant.INT_ZERO]
        }
    }
}
