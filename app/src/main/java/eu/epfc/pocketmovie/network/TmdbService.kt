package eu.epfc.pocketmovie.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TmdbService {
    private const val baseURL = "https://api.themoviedb.org/3/"
    val movieClient : TmdbHttpClient
    init {
        // create a converter JSON -> Kotlin
        val jsonConverter = GsonConverterFactory.create()
        // create a Retrofit builder
        val retrofitBuilder : Retrofit.Builder = Retrofit.Builder().addConverterFactory(jsonConverter).baseUrl(baseURL)
        // create a Retrofit instance
        val retrofit = retrofitBuilder.build()
        // create our client
        movieClient = retrofit.create(TmdbHttpClient::class.java)
    }


}