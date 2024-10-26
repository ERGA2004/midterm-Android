package com.example.midtermandroid_yerkazy

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

// Класс данных для фильма с категорией
data class Movie(val title: String, val genre: String, val category: String)

val movies = listOf(
    Movie("Inception", "Sci-Fi", "Movies"),
    Movie("The Matrix", "Sci-Fi", "Movies"),
    Movie("Interstellar", "Sci-Fi", "Movies"),
    Movie("The Godfather", "Drama", "Movies"),
    Movie("The Dark Knight", "Action", "Movies"),
    Movie("Nomads", "Historical", "Kazakh Movies"),
    Movie("Myn Bala: Warriors of the Steppe", "Historical", "Kazakh Movies"),
    Movie("Tomyris", "Historical", "Kazakh Movies"),
    Movie("Kyz Zhibek", "Romantic", "Kazakh Movies"),
    Movie("Business Kazakh Style", "Comedy", "Kazakh Movies")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(navController: NavHostController) {
    var movieList by remember { mutableStateOf(movies.toMutableList()) }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var selectedGenre by remember { mutableStateOf<String?>(null) }
    var selectedCategory by remember { mutableStateOf<String?>(null) }

    // Поля для добавления нового фильма
    var newMovieTitle by remember { mutableStateOf("") }
    var newMovieGenre by remember { mutableStateOf("") }
    var newMovieCategory by remember { mutableStateOf("") }

    // Состояние для диалога
    var showDialog by remember { mutableStateOf(false) }

    // Открытие диалога для добавления нового фильма
    fun addNewMovie() {
        if (newMovieTitle.isNotEmpty() && newMovieGenre.isNotEmpty() && newMovieCategory.isNotEmpty()) {
            movieList.add(Movie(newMovieTitle, newMovieGenre, newMovieCategory))
            newMovieTitle = ""
            newMovieGenre = ""
            newMovieCategory = ""
            showDialog = false // Закрыть диалог после добавления
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Favorite Movies") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Поле для поиска
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search Movies") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Фильтрация по жанру
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = { selectedGenre = "Sci-Fi" }) {
                    Text("Sci-Fi")
                }
                TextButton(onClick = { selectedGenre = "Drama" }) {
                    Text("Drama")
                }
                TextButton(onClick = { selectedGenre = "Action" }) {
                    Text("Action")
                }
                TextButton(onClick = { selectedGenre = null }) {
                    Text("All Genres")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Фильтрация по категориям
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = { selectedCategory = "Movies" }) {
                    Text("Movies")
                }
                TextButton(onClick = { selectedCategory = "Kazakh Movies" }) {
                    Text("Kazakh Movies")
                }
                TextButton(onClick = { selectedCategory = null }) {
                    Text("All Categories")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка для добавления фильма
            Button(
                onClick = { showDialog = true }, // Открыть диалог
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Movie")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Фильтрация фильмов по поисковому запросу, жанру и категории
            val filteredMovies = movieList.filter {
                (selectedGenre == null || it.genre == selectedGenre) &&
                        (selectedCategory == null || it.category == selectedCategory) &&
                        it.title.contains(searchQuery.text, ignoreCase = true)
            }

            // Список фильмов
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredMovies) { movie ->
                    MovieItem(
                        movie = movie,
                        navController = navController,
                        onDelete = { movieToDelete -> movieList.removeAll { it.title == movieToDelete } }
                    )
                }
            }

            // Диалог для добавления нового фильма
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Add New Movie") },
                    text = {
                        Column {
                            OutlinedTextField(
                                value = newMovieTitle,
                                onValueChange = { newMovieTitle = it },
                                label = { Text("Movie Title") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = newMovieGenre,
                                onValueChange = { newMovieGenre = it },
                                label = { Text("Movie Genre") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            OutlinedTextField(
                                value = newMovieCategory,
                                onValueChange = { newMovieCategory = it },
                                label = { Text("Movie Category") },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    },
                    confirmButton = {
                        TextButton(
                            onClick = { addNewMovie() }
                        ) {
                            Text("Submit")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { showDialog = false }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, navController: NavHostController, onDelete: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("movie_detail_screen/${movie.title}")
            }
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = movie.title,
                style = MaterialTheme.typography.titleLarge,
            )
            IconButton(onClick = { onDelete(movie.title) }) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Movie"
                )
            }
        }
    }
}
