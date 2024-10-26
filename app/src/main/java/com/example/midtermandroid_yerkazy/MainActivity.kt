package com.example.midtermandroid_yerkazy
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController() // Создание NavController

            Scaffold(
                content = {
                    NavHost(
                        navController = navController,
                        startDestination = "movie_list_screen"
                    ) {
                        composable("movie_list_screen") {
                            MovieListScreen(navController = navController) // Передача navController
                        }
                        composable("movie_detail_screen/{movieTitle}") { backStackEntry ->
                            val movieTitle = backStackEntry.arguments?.getString("movieTitle") ?: ""
                            MovieDetailScreen(navController = navController, movieName = movieTitle)
                        }
                    }
                }
            )
        }
    }
}
