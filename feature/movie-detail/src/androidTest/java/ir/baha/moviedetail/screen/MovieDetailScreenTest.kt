package ir.baha.moviedetail.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import io.mockk.every
import io.mockk.mockk
import ir.baha.moviedetail.vm.MovieDetailState
import ir.baha.moviedetail.vm.MovieDetailViewModel
import ir.baha.moviedomain.entity.MovieDetailEntity
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test


class MovieDetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testMovieDetailScreen_displaysLoadingIndicator() {
        // Arrange
        val viewModel = mockk<MovieDetailViewModel>()
        every { viewModel.uiState } returns MutableStateFlow(MovieDetailState.Loading)

        // Act
        composeTestRule.setContent {
            MovieDetailScreen(viewModel = viewModel, 1)
        }

        composeTestRule.onNodeWithTag("loading_tag").assertIsDisplayed()
    }

    @Test
    fun testMovieDetailScreen_displaysMovieDetail() {
        // Arrange
        val viewModel = mockk<MovieDetailViewModel>()
        val movieDetailEntity = MovieDetailEntity(
            "",
            listOf(),
            1,
            "",
            "movie_title",
            "",
            "",
            "",
            2.2,
            2
        )
        every { viewModel.uiState } returns MutableStateFlow(
            MovieDetailState.ShowMovieList(movieDetailEntity)
        )

        // Act
        composeTestRule.setContent {
            MovieDetailScreen(viewModel = viewModel, 1)
        }

        // Assert
        composeTestRule.onNodeWithText(movieDetailEntity.original_title).assertIsDisplayed()
    }

    @Test
    fun testMovieDetailScreen_displaysError() {
        // Arrange
        val viewModel = mockk<MovieDetailViewModel>()
        val error = "error"
        every { viewModel.uiState } returns MutableStateFlow(
            MovieDetailState.Error(error)
        )

        // Act
        composeTestRule.setContent {
            MovieDetailScreen(viewModel = viewModel, 1)
        }

        // Assert
        composeTestRule.onNodeWithText("ERROR: $error").assertIsDisplayed()
    }

}