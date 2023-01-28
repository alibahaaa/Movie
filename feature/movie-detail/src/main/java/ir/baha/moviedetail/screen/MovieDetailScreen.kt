package ir.baha.moviedetail.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import ir.baha.moviedetail.R
import ir.baha.moviedetail.vm.*
import ir.baha.moviedomain.entity.MovieDetailEntity

@Composable
fun MovieDetailScreen(
    viewModel: MovieDetailViewModel = hiltViewModel(),
    movieId: Int,
    onPop: () -> Unit,
) {
    val uiState = viewModel
        .uiState
        .collectAsState()
        .value

    when (uiState) {
        MovieDetailState.Idle -> viewModel.sendIntent(MovieDetailIntent.GetMovieDetail(movieId))
        MovieDetailState.Loading -> DetailLoadingView()
        is MovieDetailState.Error -> Text(text = "ERROR: ${uiState.error}")
        is MovieDetailState.ShowMovieList -> MovieDetailView(uiState.movie!!, onPop)
    }
}

@Composable
fun DetailLoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag("loading_tag"),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(50.dp))
    }
}

@Composable
fun MovieDetailView(
    movie: MovieDetailEntity,
    onPop: () -> Unit,
) {
    LazyColumn {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = movie.backdrop_path),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .height(200.dp),
                    alignment = Alignment.Center
                )
                Row(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = movie.poster_path),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .width(100.dp)
                            .height(150.dp)
                            .clip(RoundedCornerShape(4.dp)),
                    )

                    Column {
                        Text(
                            text = movie.original_title,
                            modifier = Modifier.padding(top = 60.dp, start = 12.dp)
                        )

                        LazyHorizontalGrid(
                            rows = GridCells.Fixed(2)
                        ) {
                            items(movie.genres) { genre ->
                                Card(
                                    modifier = Modifier
                                        .height(60.dp)
                                        .padding(4.dp),
                                    elevation = 0.dp,
                                    border = BorderStroke(1.dp, Color.LightGray),
                                    shape = RoundedCornerShape(50.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = genre.name,
                                            style = TextStyle(
                                                fontSize = 12.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
                Row(
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .align(Alignment.TopCenter),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(com.google.android.material.R.drawable.abc_ic_ab_back_material),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onPop() },
                        alignment = Alignment.Center
                    )
                    Row {
                        Image(
                            painter = painterResource(com.google.android.material.R.drawable.abc_ic_menu_share_mtrl_alpha),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(24.dp),
                            alignment = Alignment.Center
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(com.google.android.material.R.drawable.mtrl_switch_track),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(24.dp),
                            alignment = Alignment.Center
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                modifier = Modifier
                    .background(Color.LightGray)
                    .height(1.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = movie.vote_average.toString(),
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            painter = painterResource(com.google.android.material.R.drawable.abc_star_black_48dp),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(24.dp),
                            alignment = Alignment.Center
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = movie.vote_count.toString(),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(com.google.android.material.R.drawable.abc_btn_check_to_on_mtrl_000),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(24.dp),
                            alignment = Alignment.Center
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = movie.original_language,
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.language),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painter = painterResource(com.google.android.material.R.drawable.ic_clock_black_24dp),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(24.dp),
                            alignment = Alignment.Center
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = movie.release_date,
                            style = TextStyle(
                                fontSize = 12.sp
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.release_date),
                        style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Divider(
                modifier = Modifier
                    .background(Color.LightGray)
                    .height(1.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.overview),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Text(
                text = movie.overview,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }

    }
}




