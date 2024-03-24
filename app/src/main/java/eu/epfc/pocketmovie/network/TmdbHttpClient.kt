package eu.epfc.pocketmovie.network

import retrofit2.http.GET

interface TmdbHttpClient {
    @GET("movie/popular?page={your_page_number}&api_key=3e61ccbeab06aaea3faa21401638cbef ")
    suspend fun getMovies() : List<Movie>
    @GET("movie/{your_movie_id}?api_key=3e61ccbeab06aaea3faa21401638cbef")
    suspend fun getMovieDetails() : Movie
}


// obtenir les details d'un film
// https://api.themoviedb.org/3/movie/$movieID?api_key=3e61ccbeab06aaea3faa21401638cbef     remplacer {your_movie_id} par $movieID (+creer variable)

// obtenir une liste de film
// https://api.themoviedb.org/3/movie/popular?page={your_page_number}&api_key=3e61ccbeab06aaea3faa21401638cbef   remplacer {your_page_number} par $numberPage (+creer variable)