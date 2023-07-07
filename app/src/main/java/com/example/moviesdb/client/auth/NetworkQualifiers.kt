package com.example.moviesdb.client.auth

import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainNetworkModule

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MapNetworkModule
