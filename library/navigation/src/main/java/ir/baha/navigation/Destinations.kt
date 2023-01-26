package ir.baha.navigation

sealed class Destinations(val route: String) {
    object MovieListScreen : Destinations("movie_list_screen")
    data class MovieDetailScreen(val movie: String = "movie") : Destinations("movie_detail_screen")
}