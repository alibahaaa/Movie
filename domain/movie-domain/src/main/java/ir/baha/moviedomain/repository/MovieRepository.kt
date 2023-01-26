package ir.baha.moviedomain.repository

import ir.baha.moviedomain.entity.MovieDetailEntity
import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.moviedomain.entity.Resource

interface MovieRepository {

    suspend fun getMovieList(page: Int): Resource<List<MovieEntity>>

    suspend fun getMovieDetail(movieId: Int): Resource<MovieDetailEntity>

}