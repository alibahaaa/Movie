package ir.baha.moviedomain.repository

import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.moviedomain.entity.Resource

interface MovieListRepository {

    suspend fun getMovieList(): Resource<List<MovieEntity>>

}