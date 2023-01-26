package ir.baha.moviedomain.repository

import ir.baha.moviedomain.entity.MovieDetailEntity
import ir.baha.moviedomain.entity.Resource

interface MovieDetailRepository {

    suspend fun getMovieDetail(movieId: Int): Resource<MovieDetailEntity>

}