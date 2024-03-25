package eu.epfc.pocketmovie.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.epfc.pocketmovie.model.Repository
import eu.epfc.pocketmovie.network.TmdbService
import kotlinx.coroutines.launch

enum class MovieResult() {
    SUCCESS,
    ERROR,
    UNINITIALIZED;
}

class MainScreenViewModel : ViewModel() {

    val displayedActivity = mutableStateOf("")
    val fetchResult = mutableStateOf(MovieResult.UNINITIALIZED)

    init {
        fetchMovies()
        Log.d("", "ok")
    }

    fun fetchMovies(){
        viewModelScope.launch {
            try {
                val movie = Repository.loadMovies()
            }catch (e:Exception){

            }
        }
    }

    fun fetchMovieDetails(){
        viewModelScope.launch {
            try {
                val movieDetails = TmdbService.movieClient.getMovieDetails()
            }catch (e:Exception){

            }
        }
    }

}

