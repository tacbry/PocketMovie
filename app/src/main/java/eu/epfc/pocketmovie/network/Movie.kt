package eu.epfc.pocketmovie.network

data class Movie(
    val id : Int,
    val title : String,
    val release_date : String,
    val overview : String,
    val vote_average : Double,
    val genres : List<Genre>? = null,
    val originalLanguage : String?,
    var pocket : Boolean = false,
    var poster_path : String,
    val backdropPath : String? = null,
    val budget : Int = 0,
    val adult : Boolean = false,
    val productionCountries : List<Country>? = null,
    val duration : Int = 0
    )


data class Genre(
    val id: Int,
    val name : String
)

data class Country(
    val isoId : String,
    val name : String
)

