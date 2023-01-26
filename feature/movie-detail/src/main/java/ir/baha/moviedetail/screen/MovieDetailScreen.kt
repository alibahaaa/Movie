package ir.baha.moviedetail.screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import ir.baha.moviedetail.vm.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    movieId: Int
) {
    Text(text = "Hi $movieId")
}