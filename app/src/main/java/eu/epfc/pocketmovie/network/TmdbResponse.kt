package eu.epfc.pocketmovie.network


data class TmdbResponse(
    val page : Int,
    val results : List<Movie>
)

