package ir.baha.movieremote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponseEntity(
    @SerializedName("backdrop_path")
    val backdrop_path: String,

    @SerializedName("genres")
    val genres: List<GenreResponse>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("original_language")
    val original_language: String,

    @SerializedName("original_title")
    val original_title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val poster_path: String,

    @SerializedName("release_date")
    val release_date: String,

    @SerializedName("vote_average")
    val vote_average: Double,

    @SerializedName("vote_count")
    val vote_count: Int
)

data class GenreResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)