package com.example.moviesdb.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SpokenLanguage(
    @SerializedName("english_name")
    @Expose
    val englishName: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,
)
