package eu.epfc.pocketmovie.network


import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbHttpClient {
    @GET("movie/popular")
        suspend fun getMovies(@Query("page") page : Int,@Query("api_key") key :String) : TmdbResponse
    @GET("movie/{movieId}")
        suspend fun getMovieDetails(@Path("movieId") movieId : String , @Query("api_key") key :String) : Movie
}


// obtenir les details d'un film
// https://api.themoviedb.org/3/movie/693134?api_key=3e61ccbeab06aaea3faa21401638cbef

// obtenir une liste de film
// https://api.themoviedb.org/3/movie/popular?page=1&api_key=3e61ccbeab06aaea3faa21401638cbef