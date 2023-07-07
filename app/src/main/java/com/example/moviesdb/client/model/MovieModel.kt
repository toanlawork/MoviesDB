package com.example.moviesdb.client.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(tableName = "MOVIE")
@Serializable
data class MovieModel(
    @SerializedName("id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "COLUMN_MOVIE_ID")
    val id: Int? = null,

    @SerializedName("adult")
    @Expose
    @ColumnInfo(name = "COLUMN_ADULT")
    val adult: Boolean? = null,

    @SerializedName("backdrop_path")
    @Expose
    @ColumnInfo(name = "COLOUMN_BACKDROP_PATH")
    val backdropPath: String? = null,

    @SerializedName("original_language")
    @Expose
    @ColumnInfo(name = "COLUMN_ORIGINAL_LANGUAGE")
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    @Expose
    @ColumnInfo(name = "COLUMN_ORIGINAL_TITLE")
    val originalTitle: String? = null,

    @SerializedName("overview")
    @Expose
    @ColumnInfo(name = "COLUMN_OVER_VIEW")
    val overview: String? = null,

    @SerializedName("popularity")
    @Expose
    @ColumnInfo(name = "COLUMN_POPULARITY")
    val popularity: Double? = null,

    @SerializedName("poster_path")
    @Expose
    @ColumnInfo(name = "COLUMN_POSTER_PATH")
    val posterPath: String? = null,

    @SerializedName("release_date")
    @Expose
    @ColumnInfo(name = "COLUMN_REALEASE_DATE")
    val releaseDate: String? = null,

    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "COLUMN_MOVIE_TITLE")
    val title: String? = null,

    @SerializedName("video")
    @Expose
    @ColumnInfo(name = "COLUMN_MOVIE_VIDEO")
    val video: Boolean? = null,

    @SerializedName("vote_average")
    @Expose
    @ColumnInfo(name = "COLUMN_VOTE_AVERAGE")
    val voteAverage: String? = null,

    @SerializedName("vote_count")
    @Expose
    @ColumnInfo(name = "COLUMN_VOTE_COUNT")
    val voteCount: Long? = null,

    @ColumnInfo(name = "COLUMN_RUN_TIME") var runtime: Long? = null,

    @ColumnInfo(name = "COLUMN_IS_FAVORITE") var isFavorite: Boolean = false,
    @ColumnInfo(name = "GENRES") var genres: List<Genre>? = null,
)
