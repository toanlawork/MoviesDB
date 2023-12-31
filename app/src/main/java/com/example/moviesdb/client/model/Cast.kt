package com.example.moviesdb.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("adult")
    @Expose
    val adult: Boolean? = null,

    @SerializedName("gender")
    @Expose
    val gender: Int? = null,

    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("known_for_department")
    @Expose
    val knownForDepartment: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("original_name")
    @Expose
    val originalName: String? = null,

    @SerializedName("popularity")
    @Expose
    val popularity: Double? = null,

    @SerializedName("profile_path")
    @Expose
    val profilePath: Any? = null,

    @SerializedName("cast_id")
    @Expose
    val castId: Int? = null,

    @SerializedName("character")
    @Expose
    val character: String? = null,

    @SerializedName("credit_id")
    @Expose
    val creditId: String? = null,

    @SerializedName("order")
    @Expose
    val order: Int? = null,
)
