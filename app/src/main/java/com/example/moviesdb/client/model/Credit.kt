package com.example.moviesdb.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Credit(
    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("cast")
    @Expose
    val cast: List<Cast>? = null,
)
