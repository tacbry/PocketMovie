package eu.epfc.pocketmovie.model

import android.content.Context
import android.content.res.AssetManager
//import eu.epfc.pocketmovie.database.PocketDatabase
import eu.epfc.pocketmovie.network.Movie
import eu.epfc.pocketmovie.network.TmdbHttpClient
import eu.epfc.pocketmovie.network.TmdbResponse
import eu.epfc.pocketmovie.network.TmdbService
import eu.epfc.pocketmovie.network.TmdbService.movieClient
import org.json.JSONObject
import java.io.InputStream

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

    //fonction suspend pour db

    //fonction json



    suspend fun loadMovies(){
        val response = movieClient.getMovies(pageNumber,key)
        if (response!=null){
            _movies.clear()
            _movies = response.results.toMutableList()


            }
        }
    }