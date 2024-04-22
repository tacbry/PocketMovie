package eu.epfc.pocketmovie.ui

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
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
                Repository.loadDetails(movieId)
            } catch (e: Exception) {
                Log.d("fetchDetails","can't fetch details")
            }
            movieDetails.value = Repository.details.value
        }
    }

    suspend fun isPocket(movieId: Int, checked : Boolean){
        fetchMovieDetails(movieId)
        if (checked){
            movieDetails.value?.let { Repository.insertPocket(it) }
        } else{
            movieDetails.value?.let { Repository.deletePocket(it) }
        }
    }

    fun switchChange(checked: Boolean,movieId: Int){
        viewModelScope.launch(Dispatchers.IO){
            try {
                isPocket(movieId,checked)
            }catch(e : Exception){
                Log.d("switchChange","can't switch")
            }
        }
    }
}




@Composable
fun ViewPosterCoil(poster: String) {
    val url = "https://image.tmdb.org/t/p/w500$poster"
    AsyncImage(model = url, contentDescription = "poster",
        modifier = Modifier.size(width = 100.dp, height = 200.dp) )
}

@Composable

fun CountryFlag(codePays: String) {
    var code = codePays.lowercase()
    val url = "https://flagcdn.com/h20/$code.png"
    AsyncImage(model = url, contentDescription = "languageflag")
}


class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel() as T
    }
}


