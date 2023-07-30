package com.example.moviesdb.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Messages(
    @SerialName("en")
    val en: String,
    @SerialName("vn")
    val vn: String,
)
