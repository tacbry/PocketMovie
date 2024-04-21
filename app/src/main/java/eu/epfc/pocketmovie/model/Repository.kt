package eu.epfc.pocketmovie.model


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import eu.epfc.pocketmovie.network.Movie
import eu.epfc.pocketmovie.network.TmdbService.movieClient


object Repository {
    var pageNumber : Int = 1
    const val  key : String = "3e61ccbeab06aaea3faa21401638cbef"

    /*
            //Database
    private var database : PocketDatabase?=null
    fun initDatabase(context : Context){
        if(database==null){
            database = PocketDatabase.getInstance(context)
        }
    }*/

    //Http
    private var _movies: MutableList<Movie> = mutableListOf()
    val movies : List<Movie>
        get() = _movies

    private var _details: MutableState<Movie?> = mutableStateOf(null)
    val details : MutableState<Movie?>
        get() = _details

    //fonction suspend pour db

    //fonction json



    suspend fun loadMovies(){
        val response = movieClient.getMovies(pageNumber,key)
        _movies = response.results.toMutableList()
            //concaten liste actuelle et nouvelle (peut etre dans fetchmovies)

        }


    suspend fun loadDetails(movieId : Int){
        val response = movieClient.getMovieDetails(movieId.toString(),key)
        _details.value = response
            //concaten liste actuelle et nouvelle (peut etre dans fetchmovies)

    }
}

