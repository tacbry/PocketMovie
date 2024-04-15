package eu.epfc.pocketmovie.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import eu.epfc.pocketmovie.model.Repository
import eu.epfc.pocketmovie.model.Repository.key
import eu.epfc.pocketmovie.network.Movie
import eu.epfc.pocketmovie.network.TmdbService
import eu.epfc.pocketmovie.network.TmdbService.movieClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class MovieResult() {
    SUCCESS,
    ERROR,
    UNINITIALIZED;
}


class MainScreenViewModel() : ViewModel() {
    val movies: MutableState<List<Movie>> = mutableStateOf(listOf())
    val movieDetails : MutableState<Movie?> = mutableStateOf(null)
    val displayedActivity = mutableStateOf("")
    val fetchResult = mutableStateOf(MovieResult.UNINITIALIZED)

    init {
        //fetchMovies()
        Log.d("HTTP Call", "ok")
        //fetchMovieDetails(693134)
        //Log.d("HTTP Detail Call ", "ok")
    }

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Repository.loadMovies()
            } catch (e: Exception) {

            }
            movies.value = Repository.movies
        }
    }

    fun fetchMovieDetails(movieId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                movieDetails.value = movieClient.getMovieDetails(movieId.toString(),key)
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


