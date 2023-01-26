package ir.baha.navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ir.baha.moviedetail.screen.MovieDetailScreen
import ir.baha.moviedomain.entity.MovieEntity
import ir.baha.navigation.destination.Destinations
import ir.baha.navigation.extension.parcelableData

fun NavGraphBuilder.movieDetailGraph(
    navController: NavController
) {
    composable(Destinations.MovieDetailScreen().route) { entry ->
        val movie = entry.parcelableData<MovieEntity>(Destinations.MovieDetailScreen().movie)
        MovieDetailScreen(movieId = movie?.id ?: 0)
    }
}