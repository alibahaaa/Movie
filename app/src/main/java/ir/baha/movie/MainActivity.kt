package ir.baha.movie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.baha.movie.navigation.MovieNavHost
import ir.baha.movie.ui.theme.MovieTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme {
                val navController = rememberNavController()
                Scaffold { paddingValues ->
                    MovieNavHost(
                        navController = navController,
                        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
                    )
                }
            }
        }
    }
}