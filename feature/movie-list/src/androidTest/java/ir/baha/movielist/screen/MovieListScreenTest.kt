package ir.baha.movielist.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import io.mockk.every
import io.mockk.mockk
import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.movielist.vm.MovieListState
import ir.baha.movielist.vm.MovieListViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test


class MovieListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val onNavigateToDetailScreen: (movieId: MovieEntity?) -> Unit = { }

    @Test
    fun testMovieListScreen_displaysLoadingIndicator() {
        // Arrange
        val viewModel = mockk<MovieListViewModel>()
        every { viewModel.uiState } returns MutableStateFlow(MovieListState.Loading)

        // Act
        composeTestRule.setContent {
            MovieListScreen(viewModel = viewModel, onNavigateToDetailScreen)
        }

        // Assert
        composeTestRule.onNodeWithTag("loading_tag").assertIsDisplayed()
    }

    @Test
    fun testMovieListScreen_displaysMovieItems() {
        // Arrange
        val viewModel = mockk<MovieListViewModel>()
        val movieEntity1 = MovieEntity(1, "title1", "image1")
        val movieEntity2 = MovieEntity(2, "title2", "image2")
        every { viewModel.uiState } returns MutableStateFlow(
            MovieListState.Pager(
                movies = flowOf(
                    PagingData.from(listOf(movieEntity1, movieEntity2))
                )
            )
        )

        // Act
        composeTestRule.setContent {
            MovieListScreen(viewModel = viewModel, onNavigateToDetailScreen)
        }

        // Assert
        composeTestRule.onNodeWithText(movieEntity1.title).assertIsDisplayed()
        composeTestRule.onNodeWithText(movieEntity2.title).assertIsDisplayed()
    }

    @Test
    fun testMovieListScreen_displaysError() {
        // Arrange
        val viewModel = mockk<MovieListViewModel>()
        every { viewModel.uiState } returns MutableStateFlow(
            MovieListState.Pager(
                movies = flowOf(
                    PagingData.from(
                        emptyList(),
                        sourceLoadStates = LoadStates(
                            append = LoadState.Error(Exception("")),
                            refresh = LoadState.NotLoading(false),
                            prepend = LoadState.NotLoading(false),
                        )
                    )
                )
            )
        )

        // Act
        composeTestRule.setContent {
            MovieListScreen(viewModel = viewModel, onNavigateToDetailScreen)
        }

        // Assert
        composeTestRule.onNodeWithTag("movie_list_error_view").assertIsDisplayed()
    }


    @Test
    fun testMovieListScreen_navigatesToDetailScreen() {
        // Arrange
        val viewModel = mockk<MovieListViewModel>()
        val movieEntity = MovieEntity(1, "image1", "title1")
        every { viewModel.uiState } returns MutableStateFlow(
            MovieListState.Pager(
                movies = flowOf(
                    PagingData.from(listOf(movieEntity))
                )
            )
        )
        var selectedMovie: MovieEntity? = null
        val onNavigateToDetailScreen: (movieId: MovieEntity?) -> Unit = { selectedMovie = it }

        // Act
        composeTestRule.setContent {
            MovieListScreen(
                viewModel = viewModel,
                onNavigateToDetailScreen = onNavigateToDetailScreen
            )
        }
        composeTestRule.onNodeWithText(movieEntity.title).performClick()

        // Assert
        assertEquals(movieEntity, selectedMovie)
    }

}

