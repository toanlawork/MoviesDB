package com.example.moviesdb.client.auth

interface ConnectionListener {
    fun onInternetUnavailable()
    fun tokenExpired()
    fun accessDenied()
}
