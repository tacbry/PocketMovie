package eu.epfc.pocketmovie.network

data class Movie(
    val id: Int,
    val title: String,
    val release_date: String,
    val overview: String,
    val vote_average: Double,
    val genres: List<Genre>,
    val original_language: String,
    var pocket: Boolean = false,
    var poster_path: String,
    val backdropPath: String? = null,
    val budget: Int = 0,
    val adult: Boolean = false,
    val production_countries: Country,
    val duration: Int = 0
    )


data class Genre(
    val id: Int,
    val name : String
)

data class Country(
    val iso_3166_1 : String,
    val name : String
)

