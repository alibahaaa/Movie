package ir.baha.moviedomain.usecase

import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.moviedomain.entity.Resource
import ir.baha.moviedomain.repository.MovieRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(): Resource<List<MovieEntity>> = repository.getMovieList()

}