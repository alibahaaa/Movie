package ir.baha.navigation.graph

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ir.baha.movielist.screen.MovieListScreen
import ir.baha.navigation.destination.Destinations
import ir.baha.navigation.extension.navigate

fun NavGraphBuilder.movieListGraph(
    navController: NavController
) {
    composable(Destinations.MovieListScreen.route) {
        MovieListScreen(
            onNavigateToDetailScreen = { movie ->
                navController.navigate(
                    route = Destinations.MovieDetailScreen().route,
                    args = bundleOf(Destinations.MovieDetailScreen().movie to movie)
                )
            }
        )
    }
}