package ir.baha.movierepository.repository

import ir.baha.moviedomain.entity.MovieDetailEntity
import ir.baha.moviedomain.entity.Resource
import ir.baha.moviedomain.repository.MovieDetailRepository
import ir.baha.movieremote.api.MovieApi
import ir.baha.movieremote.mapper.toMovieDetail
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int): Resource<MovieDetailEntity> = try {
        val response = api.getMovieDetail(movie_id = movieId)

        if (response.isSuccessful) {
            Resource.Success(response.body()?.toMovieDetail()!!)
        } else {
            Resource.Error(Exception(response.message() ?: "An unknown error occurred"))
        }
    } catch (e: Exception) {
        Resource.Error(e)
    }

}