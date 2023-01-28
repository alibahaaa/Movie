package ir.baha.movierepository.repository

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import ir.baha.moviedomain.entity.MovieDetailEntity
import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.moviedomain.entity.Resource
import ir.baha.moviedomain.repository.MovieRepository
import ir.baha.movieremote.api.MovieApi
import ir.baha.movieremote.response.MovieDetailResponseEntity
import ir.baha.movieremote.response.MovieListResponseEntity
import ir.baha.movieremote.response.MovieResponseEntity
import ir.baha.movierepository.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class MovieRepositoryImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var movieRepository: MovieRepository
    private lateinit var api: MovieApi

    private val movie = MovieEntity(1, "title", "https://image.tmdb.org/t/p/w500/image.png")
    private val movieResponseEntity = MovieResponseEntity(1, "title", "image.png")
    private val movieListResponseEntity = MovieListResponseEntity(1, listOf(movieResponseEntity))

    private val movieDetailResponseEntity = MovieDetailResponseEntity(
        "",
        listOf(),
        1,
        "",
        "",
        "",
        "",
        "",
        2.2,
        2
    )

    private val movieDetailEntity = MovieDetailEntity(
        "",
        listOf(),
        1,
        "",
        "",
        "",
        "",
        "",
        2.2,
        2
    )

    private val expectedResource = Resource.Error(Exception("Error message"), null)
    private val testException = Resource.Error(Exception("Test exception"), null)

    @Before
    fun setUp() {
        api = mockk()
        movieRepository = MovieRepositoryImpl(api)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getMovieList should return a success resource when API call is successful`() = runTest {
        // given
        coEvery { api.getMovies(any(), any()) } returns Response.success(movieListResponseEntity)

        // when
        val res = movieRepository.getMovieList(1)

        // expect
        assertThat(res.data).isNotNull()
        assertThat(res.data!!.size).isEqualTo(1)
        assertThat(res.data!![0].image).isEqualTo(movie.image)
        assertThat(res.exception).isNull()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getMovieList should return an error resource when API call is not successful`() = runTest {
        // given
        coEvery { api.getMovies(any(), any()) } returns Response.error(
            404, "Error message".toResponseBody()
        )

        // when
        val res = movieRepository.getMovieList(0)

        // expect
        assertThat(res.exception?.message).isEqualTo(expectedResource.exception?.message)
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getMovieList should return an error resource when API call throws an exception`() =
        runTest {
            // given
            coEvery { api.getMovies(any(), any()) } throws Exception("Test exception")

            // when
            val res = movieRepository.getMovieList(1)

            // expect
            assertThat(res.exception?.message).isEqualTo(testException.exception?.message)
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getMovieDetail should return a success resource when API call is successful`() = runTest {

        // given
        coEvery { api.getMovieDetail(any(), any()) } returns Response.success(
            movieDetailResponseEntity
        )

        // when
        val res = movieRepository.getMovieDetail(1)

        // expect
        assertThat(res.data).isNotNull()
        assertThat(res.data!!.id).isEqualTo(movieDetailEntity.id)
        assertThat(res.exception).isNull()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getMovieDetail should return an error resource when API call is not successful`() =
        runTest {
            // given
            coEvery { api.getMovieDetail(any(), any()) } returns Response.error(
                404, "Error message".toResponseBody()
            )

            // when
            val res = movieRepository.getMovieDetail(0)

            // expect
            assertThat(res.exception?.message).isEqualTo(expectedResource.exception?.message)
        }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getMovieDetail should return an error resource when API call throws an exception`() =
        runTest {
            // given
            coEvery { api.getMovieDetail(any(), any()) } throws Exception("Test exception")

            // when
            val res = movieRepository.getMovieDetail(1)

            // expect
            assertThat(res.exception?.message).isEqualTo(testException.exception?.message)
        }

}