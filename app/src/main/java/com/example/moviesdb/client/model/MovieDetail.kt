package com.example.moviesdb.client.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("adult")
    @Expose
    val adult: Boolean? = null,

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = null,

    @SerializedName("belongs_to_collection")
    @Expose
    val credit: Credit?,

    @SerializedName("budget")
    @Expose
    val budget: Int? = null,

    @SerializedName("genres")
    @Expose
    val genres: List<Genre>? = null,

    @SerializedName("homepage")
    @Expose
    val homepage: String? = null,

    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("imdb_id")
    @Expose
    val imdbId: String? = null,

    @SerializedName("original_language")
    @Expose
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    @Expose
    val originalTitle: String? = null,

    @SerializedName("overview")
    @Expose
    val overview: String? = null,

    @SerializedName("popularity")
    @Expose
    val popularity: Double? = null,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null,

    @SerializedName("production_companies")
    @Expose
    val productionCompanies: List<Any>? = null,

    @SerializedName("production_countries")
    @Expose
    val productionCountries: List<ProductionCountry>? = null,

    @SerializedName("release_date")
    @Expose
    val releaseDate: String? = null,

    @SerializedName("runtime")
    @Expose
    val runtime: Long? = null,

    @SerializedName("spoken_languages")
    @Expose
    val spokenLanguages: List<SpokenLanguage>? = null,

    @SerializedName("status")
    @Expose
    val status: String? = null,

    @SerializedName("tagline")
    @Expose
    val tagline: String? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("video")
    @Expose
    val video: Boolean? = null,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: String? = null,

    @SerializedName("vote_count")
    @Expose
    val voteCount: Long? = null,
) {
    constructor(id: Int?, runTime: Long?, genres: List<Genre>?) :
        this(
            null,
            null,
            null,
            null,
            genres,
            null,
            id,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            runTime,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
        )
}
