package ir.baha.movieremote.mapper

import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.movieremote.response.MovieResponseEntity

fun MovieResponseEntity.toMovieEntity(): MovieEntity = MovieEntity(
    title = title,
    id = id,
    image = image
)
