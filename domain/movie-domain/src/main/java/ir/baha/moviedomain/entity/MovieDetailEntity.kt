package ir.baha.moviedomain.entity

data class MovieDetailEntity(
    val backdrop_path: String,
    val genres: List<Genre>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val vote_average: Double,
    val vote_count: Int
)

data class Genre(
    val id: Int,
    val name: String
)