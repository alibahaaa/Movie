package ir.baha.movierepository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.baha.moviedomain.repository.MovieRepository
import ir.baha.movierepository.repository.MovieRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindMovieListRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

}