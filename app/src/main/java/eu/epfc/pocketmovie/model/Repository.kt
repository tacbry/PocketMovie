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
    val pageNumber : Int = 1
    var key : String = "3e61ccbeab06aaea3faa21401638cbef"

    /*
            //Database
    private var database : PocketDatabase?=null
    fun initDatabase(context : Context){
        if(database==null){
            database = PocketDatabase.getInstance(context)
        }
    }*/

    //Http
    private val _movies: MutableList<Movie> = mutableListOf()
    val movies : List<Movie>
        get() = _movies

    //fonction suspend pour db

    //fonction json



    suspend fun loadMovies(){
        val response = movieClient.getMovies(pageNumber,key)
        if (response!=null){
            _movies.clear()
            val rootJSON = JSONObject(response.results.toString())
            val rootJSONArray = JSONObject(response.results.toString())
            val results = rootJSON.getJSONArray("results")
            for (i in 0 until results.length()){
                val movieJSON = results.getJSONObject(i)
                val id = movieJSON.getInt("id")
                val title = movieJSON.getString("title")
                val release_date = movieJSON.getString("release_date")
                val overview = movieJSON.getString("overview")
                val vote_average = movieJSON.getDouble("vote_average")
                val genre_ids = movieJSON.getInt("genre_ids")
                val original_language = movieJSON.getString("original_language")

                val movie = Movie(id,title,release_date,overview,vote_average,original_language=original_language) //comment faire avec le array genre ids
                _movies.add(movie)
            }
        }
    }
}