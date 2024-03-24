package eu.epfc.pocketmovie.network

data class Movie(
    val movieId : Int,
    val title : String,
    val year : String,
    val summary : String,
    val ratings : Float,
    val genre : List<String>,//creer une classe  genre List<classe>
    val originalLanguage : String,
    var pocket : Boolean
)

