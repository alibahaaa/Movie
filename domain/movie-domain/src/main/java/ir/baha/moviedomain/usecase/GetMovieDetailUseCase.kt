package ir.baha.moviedomain.usecase

import ir.baha.moviedomain.entity.MovieDetailEntity
import ir.baha.moviedomain.entity.Resource
import ir.baha.moviedomain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movieId: Int): Resource<MovieDetailEntity> =
        repository.getMovieDetail(movieId = movieId)

}