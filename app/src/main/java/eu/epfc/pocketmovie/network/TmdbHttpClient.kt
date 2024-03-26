package eu.epfc.pocketmovie.network


import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbHttpClient {
    @GET("movie/popular")
        suspend fun getMovies(@Query("page") page : Int,@Query("api_key") key :String) : TmdbResponse
    @GET("movie/{your_movie_id}?api_key=3e61ccbeab06aaea3faa21401638cbef")
        suspend fun getMovieDetails() : TmdbResponse
}


// obtenir les details d'un film
// https://api.themoviedb.org/3/movie/$movieID?api_key=3e61ccbeab06aaea3faa21401638cbef

// obtenir une liste de film
// https://api.themoviedb.org/3/movie/popular?page={your_page_number}&api_key=3e61ccbeab06aaea3faa21401638cbef