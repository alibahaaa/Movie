package ir.baha.movielist.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.movielist.vm.MovieListIntent
import ir.baha.movielist.vm.MovieListState
import ir.baha.movielist.vm.MovieListViewModel

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    onNavigateToDetailScreen: (movieId: MovieEntity) -> Unit,
) {
    val uiState = viewModel
        .uiState
        .collectAsState()
        .value

    when (uiState) {
        MovieListState.Loading -> Text("Loading")
        is MovieListState.Error -> Text("Error")
        is MovieListState.ShowMovieList -> {
            LazyColumn {
                items(uiState.movies) { movie ->
                    Text(
                        text = movie.title,
                        modifier = Modifier.clickable {
                            onNavigateToDetailScreen(movie)
                        }
                    )
                }
            }
        }
        MovieListState.Idle -> viewModel.sendIntent(MovieListIntent.GetMovieList)
    }

}