package eu.epfc.pocketmovie.network

data class Movie(
    val id : Int,
    val title : String,
    val release_date : String,
    val overview : String,
    val vote_average : Double,
    val genre_ids : List<String>?=null,//creer une classe  genre List<classe>
    val original_language : String,
    var pocket : Boolean = false,
    var poster_path : String
)

