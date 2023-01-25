package ir.baha.movieremote.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.baha.movieremote.api.BASE_URL
import ir.baha.movieremote.api.MovieApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor { message ->
            println("LOG-NET: $message")
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideMovieApi(
        retrofit: Retrofit
    ): MovieApi = retrofit.create(MovieApi::class.java)

}