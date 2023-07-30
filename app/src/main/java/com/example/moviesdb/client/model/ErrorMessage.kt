package com.example.moviesdb.client.model

import java.lang.Exception

data class ErrorMessage(
    val code: String = "",
    val errorCode: String = "",
    val messages: Messages? = null,
    val exception: Exception? = null
)
