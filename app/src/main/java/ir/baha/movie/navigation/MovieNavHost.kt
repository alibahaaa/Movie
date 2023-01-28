package ir.baha.movie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import ir.baha.navigation.destination.Destinations
import ir.baha.navigation.graph.movieDetailGraph
import ir.baha.navigation.graph.movieListGraph

@Composable
fun MovieNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.MovieListScreen.route
    ) {
        movieListGraph(navController)
        movieDetailGraph(navController)
    }
}