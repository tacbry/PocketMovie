package eu.epfc.pocketmovie.model


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import eu.epfc.pocketmovie.database.PocketDatabase
import eu.epfc.pocketmovie.database.PocketItem
import eu.epfc.pocketmovie.network.Movie
import eu.epfc.pocketmovie.network.TmdbService.movieClient
import eu.epfc.pocketmovie.ui.MainScreenViewModel
import eu.epfc.pocketmovie.R


object Repository {
    var pageNumber : Int = 1
    const val  key : String = "3e61ccbeab06aaea3faa21401638cbef"


    /*---------------------- Initialisation de la db + actions ----------------------*/

    private var database : PocketDatabase?=null
    fun initDatabase(context : Context){
        if(database==null){
            database = PocketDatabase.getInstance(context)
        }
    }

    suspend fun insertPocket(movie : Movie){
        database?.let { theDatabase ->
            val newPocket = PocketItem(movie)
            theDatabase.theDAO().insertPocket(newPocket)
        }
    }

    suspend fun deletePocket(movie: Movie){
        database?.let { theDatabase ->
            val oldPocket = movie.id
            theDatabase.theDAO().deletePocket(oldPocket)
        }
    }

    suspend fun getAllPocket() : List<PocketItem> {
        database?.let{theDatabase ->
            return theDatabase.theDAO().getAllPocket()
        }
        return listOf()
    }

    suspend fun isInDb(movieId: Int) : Boolean {
        database?.let{theDatabase ->
            return theDatabase.theDAO().isMovieInDb(movieId)
        }
        return false
    }


    /*---------------------- Fonction appel http ----------------------*/

    private var _movies: MutableList<Movie> = mutableListOf()
    val movies : List<Movie>
        get() = _movies

    private var _details: MutableState<Movie?> = mutableStateOf(null)
    val details : MutableState<Movie?>
        get() = _details

    suspend fun loadMovies(){
        val response = movieClient.getMovies(pageNumber,key)
        _movies = response.results.toMutableList()
    }

    suspend fun loadDetails(movieId : Int){
        val response = movieClient.getMovieDetails(movieId.toString(),key)
        _details.value = response
    }

    /*---------------------- Chargement des images ----------------------*/
    @Composable
    fun ViewPosterCoil(poster: String) {
        val url = "https://image.tmdb.org/t/p/w500$poster"
        AsyncImage(model = url, contentDescription = "poster",
            modifier = Modifier.size(width = 125.dp, height = 200.dp) )
    }

    @Composable
    fun ViewPosterDetailCoil(poster: String) {
        val url = "https://image.tmdb.org/t/p/w500$poster"
        if(url != null){
        AsyncImage(model = url, contentDescription = "poster",
            modifier = Modifier.fillMaxWidth())
        }else{
            Image(painter = painterResource(id = R.drawable.roundlogo), contentDescription = "no image" )
        }//ne règle toujours pas le problème d'absence d'image
    }

    @Composable

    fun CountryFlag(codePays: String) {
        var code = codePays.lowercase()
        if (codePays == null){
            code = "unknown"
        }
        if(code == "unknown"){

        }else{
            val url = "https://flagcdn.com/h20/$code.png"
            AsyncImage(model = url, contentDescription = "origin country flag", modifier = Modifier
                .padding(6.dp)
                .size(24.dp, 12.dp))
        }
    }
}

