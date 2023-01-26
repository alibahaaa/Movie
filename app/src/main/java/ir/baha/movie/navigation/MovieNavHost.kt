package ir.baha.movie.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ir.baha.navigation.destination.Destinations
import ir.baha.navigation.graph.movieDetailGraph
import ir.baha.navigation.graph.movieListGraph

@Composable
fun MovieNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.MovieListScreen.route,
        modifier = modifier,
    ) {
        movieListGraph(navController)
        movieDetailGraph(navController)
    }
}