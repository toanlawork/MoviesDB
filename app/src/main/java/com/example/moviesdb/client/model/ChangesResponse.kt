package com.example.moviesdb.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ChangesResponse(
    @SerializedName("page")
    @Expose
    val page: Int? = null,
    @SerializedName("results")
    @Expose
    val results: List<ChangesResult>? = null,
    @SerializedName("total_pages")
    @Expose
    val total_pages: Int? = null,
    @SerializedName("total_results")
    @Expose
    val total_results: Int? = null,
)

@Serializable
data class ChangesResult(
    @SerializedName("adult")
    @Expose
    val adult: Boolean = false,
    @SerializedName("id")
    @Expose
    val id: Int? = null,
)
