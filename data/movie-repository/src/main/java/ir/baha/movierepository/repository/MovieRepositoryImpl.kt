package ir.baha.movierepository.repository

import ir.baha.moviedomain.entity.MovieDetailEntity
import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.moviedomain.entity.Resource
import ir.baha.moviedomain.repository.MovieRepository
import ir.baha.movieremote.api.MovieApi
import ir.baha.movieremote.mapper.toMovieDetail
import ir.baha.movieremote.mapper.toMovieEntity
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    private var page: Int = 1

    override suspend fun getMovieList(): Resource<List<MovieEntity>> = try {
        Resource.Loading(null)

        val response = api.getMovies(page = page)

        if (response.isSuccessful) {
            page = response.body()?.page?.plus(1) ?: (page + 1)

            Resource.Success(
                response.body()?.results?.map { it.toMovieEntity() } ?: listOf()
            )
        } else {
            Resource.Error(Exception(response.message() ?: "An unknown error occurred"))
        }

    } catch (e: Exception) {
        Resource.Error(e)
    }

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