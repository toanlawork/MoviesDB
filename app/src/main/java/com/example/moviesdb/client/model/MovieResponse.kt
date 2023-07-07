package com.example.moviesdb.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    @SerializedName("page")
    @Expose
    val page: Int? = null,

    @SerializedName("results")
    @Expose
    var results: List<MovieModel>,

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = null,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int? = null,
)
