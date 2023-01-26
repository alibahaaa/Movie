package ir.baha.movieremote.response

import com.google.gson.annotations.SerializedName

data class MovieListResponseEntity(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieResponseEntity>
)

data class MovieResponseEntity(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("backdrop_path")
    val image: String
)