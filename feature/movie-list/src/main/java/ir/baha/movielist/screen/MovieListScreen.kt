package ir.baha.movielist.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import ir.baha.designsystem.widget.MovieListErrorView
import ir.baha.designsystem.widget.MovieListLoadingView
import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.movielist.extension.items
import ir.baha.movielist.vm.MovieListIntent
import ir.baha.movielist.vm.MovieListState
import ir.baha.movielist.vm.MovieListViewModel

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    onNavigateToDetailScreen: (movieId: MovieEntity?) -> Unit,
) {
    val uiState = viewModel
        .uiState
        .collectAsState()
        .value

    when (uiState) {
        MovieListState.Idle -> viewModel.sendIntent(MovieListIntent.GetMovieList)
        MovieListState.Loading -> CircularProgressIndicator(modifier = Modifier.testTag("loading_tag"))
        is MovieListState.Pager -> {
            val movies = uiState.movies.collectAsLazyPagingItems()

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(horizontal = 8.dp),
            ) {
                items(movies) { movie ->
                    MovieItem(
                        movieEntity = movie!!,
                        onNavigateToDetailScreen = onNavigateToDetailScreen
                    )
                }

                when (movies.loadState.append) {
                    is LoadState.NotLoading -> items(20) { MovieListLoadingView() }
                    LoadState.Loading -> {
                        items(10) { MovieListLoadingView() }
                    }
                    is LoadState.Error -> {
                        item {
                            MovieListErrorView((movies.loadState.append as LoadState.Error).error.message.toString())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(
    movieEntity: MovieEntity,
    onNavigateToDetailScreen: (movieId: MovieEntity?) -> Unit,
) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .clickable {
                onNavigateToDetailScreen(movieEntity)
            },
        backgroundColor = Color.DarkGray
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = movieEntity.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(150.dp)
            )
            Text(
                text = movieEntity.title,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

