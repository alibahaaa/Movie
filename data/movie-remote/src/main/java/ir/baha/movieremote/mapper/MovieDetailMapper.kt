package ir.baha.movieremote.mapper

import ir.baha.moviedomain.entity.GenreEntity
import ir.baha.moviedomain.entity.MovieDetailEntity
import ir.baha.movieremote.response.GenreResponse
import ir.baha.movieremote.response.MovieDetailResponseEntity

fun MovieDetailResponseEntity.toMovieDetail(): MovieDetailEntity = MovieDetailEntity(
    original_language = original_language,
    id = id,
    backdrop_path = backdrop_path,
    genres = genres.map { it.toGenreEntity() },
    original_title = original_title,
    overview = overview,
    poster_path = poster_path,
    release_date = release_date,
    vote_average = vote_average,
    vote_count = vote_count
)


fun GenreResponse.toGenreEntity(): GenreEntity = GenreEntity(
    id = id,
    name = name
)