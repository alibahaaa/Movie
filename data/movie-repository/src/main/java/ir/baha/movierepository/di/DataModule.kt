package ir.baha.movierepository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.baha.moviedomain.repository.MovieDetailRepository
import ir.baha.moviedomain.repository.MovieListRepository
import ir.baha.movierepository.repository.MovieDetailRepositoryImpl
import ir.baha.movierepository.repository.MovieListRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindMovieListRepository(
        movieListRepositoryImpl: MovieListRepositoryImpl
    ): MovieListRepository

    @Binds
    fun bindMovieDetailRepository(
        movieDetailRepositoryImpl: MovieDetailRepositoryImpl
    ): MovieDetailRepository

}