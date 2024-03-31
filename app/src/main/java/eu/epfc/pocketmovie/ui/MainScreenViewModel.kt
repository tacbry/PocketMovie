package eu.epfc.pocketmovie.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.epfc.pocketmovie.model.Repository
import eu.epfc.pocketmovie.network.Movie
import eu.epfc.pocketmovie.network.TmdbService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

enum class MovieResult() {
    SUCCESS,
    ERROR,
    UNINITIALIZED;
}

class MainScreenViewModel() : ViewModel() {
    val movies: MutableState<List<Movie>> = mutableStateOf(listOf())
    val displayedActivity = mutableStateOf("")
    val fetchResult = mutableStateOf(MovieResult.UNINITIALIZED)

    init {
        fetchMovies()
        Log.d("HTTP Call", "ok")
    }

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Repository.loadMovies()
            } catch (e: Exception) {

            }
            movies.value = Repository.movies.map { movie: Movie ->
                Movie(
                    movie.id,
                    movie.title,
                    movie.release_date,
                    movie.overview,
                    movie.vote_average,
                    original_language = movie.original_language
                )
            }
        }
    }

    fun fetchMovieDetails() {
        viewModelScope.launch {
            try {
                val movieDetails = TmdbService.movieClient.getMovieDetails()
            } catch (e: Exception) {

            }
        }
    }
}

class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel() as T
    }
}


