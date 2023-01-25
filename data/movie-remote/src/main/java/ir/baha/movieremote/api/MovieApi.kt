package ir.baha.movieremote.api

import ir.baha.movieremote.response.MovieDetailResponseEntity
import ir.baha.movieremote.response.MovieListResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/movie/popular")
    suspend fun getMovies(
        @Query("api_key")
        apiKey: String = API_KEY,
        @Query("page")
        page: Int
    ): Response<MovieListResponseEntity?>

    @GET("/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id")
        movie_id: String,
        @Query("api_key")
        apiKey: String = API_KEY
    ): Response<MovieDetailResponseEntity?>

}

internal const val API_KEY = "55957fcf3ba81b137f8fc01ac5a31fb5"
internal const val BASE_URL = "https://api.themoviedb.org/3"