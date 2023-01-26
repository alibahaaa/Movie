package ir.baha.moviedetail.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import ir.baha.moviedetail.vm.*

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    movieId: Int
) {

    val uiState = viewModel
        .uiState
        .collectAsState()
        .value

    when(uiState){
        MovieDetailState.Idle -> viewModel.sendIntent(MovieDetailIntent.GetMovieDetail(movieId))
        MovieDetailState.Loading -> Text(text = "Hi $movieId")
        is MovieDetailState.Error -> Text(text = "bye $movieId")
        is MovieDetailState.ShowMovieList -> Text(text = "Hi $movieId ${uiState.movie?.overview}")
    }

}