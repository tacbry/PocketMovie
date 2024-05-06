package eu.epfc.pocketmovie.ui

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import eu.epfc.pocketmovie.database.PocketDAO
import eu.epfc.pocketmovie.database.PocketItem
import eu.epfc.pocketmovie.model.Repository
import eu.epfc.pocketmovie.network.Genre
import eu.epfc.pocketmovie.network.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainScreenViewModel() : ViewModel() {
    val movies: MutableState<List<Movie>> = mutableStateOf(listOf())
    val movieDetails : MutableState<Movie?> = mutableStateOf(null)
    val pocketList : MutableState<List<PocketItem>> = mutableStateOf(listOf())
    var switchIsOn : MutableState<Boolean> = mutableStateOf(false)

    init {
        fetchMovies()
    }

/*---------------------- Import des items ----------------------*/
    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.loadMovies()
            movies.value += Repository.movies
        }
    }

    fun fetchMovieDetails(movieId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            switchIsOn.value = Repository.isInDb(movieId)
            try {
                Repository.loadDetails(movieId)
            } catch (e: Exception) {
                Log.d("fetchDetails","can't fetch details")
            }
            movieDetails.value = Repository.details.value
        }
    }

/*---------------------- Gestion du switch Pocket ----------------------*/

    suspend fun isPocket(movieId: Int, checked : Boolean){
        if (checked){
            //movieDetails.value?.pocket = true
            switchIsOn.value = true
            movieDetails.value?.let { Repository.insertPocket(it) }
        } else{
            //movieDetails.value?.pocket = false
            switchIsOn.value = false
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

/*---------------------- Import des items Pocket ----------------------*/
    fun fetchPocket() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
               pocketList.value = Repository.getAllPocket()
            }catch (e : Exception){

            }
        }
    }

/*---------------------- Gestion genres et country ----------------------*/
    fun mergeGenre(genres: List<Genre>): String {
        return genres.joinToString(separator = ", ") { it.name }
    }

}

    fun mergeCountry(country: List<String>): String {
        return country.joinToString(){it}
    }

/*---------------------- MainviewmodelFactory ----------------------*/

class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainScreenViewModel() as T
    }
}




