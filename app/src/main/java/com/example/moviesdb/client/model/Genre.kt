package com.example.moviesdb.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("name")
    @Expose
    val name: String?,

    var posterPath: String? = null,
)
