package eu.epfc.pocketmovie.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.epfc.pocketmovie.network.Country
import eu.epfc.pocketmovie.network.Genre
import eu.epfc.pocketmovie.network.Movie

@Entity(tableName =  "PocketItem")
data class PocketItem(
    @PrimaryKey(autoGenerate=true)
    val movieId: Int,
    val title: String,
    val release_date: String,
    val overview: String,
    val vote_average: Double,
    val genres: List<Genre>,
    val original_language: String,
    var pocket: Boolean = false,
    var poster_path: String,
    val backdrop_path: String,
    val budget: Int = 0,
    val adult: Boolean = false,
    val production_countries: List<Country>,
    val duration: Int = 0
){
    constructor(movie : Movie) : this(
        movie.id,
        movie.title,
        movie.release_date,
        movie.overview,
        movie.vote_average,
        movie.genres,
        movie.original_language,
        movie.pocket,
        movie.poster_path,
        movie.backdrop_path,
        movie.budget,
        movie.adult,
        movie.production_countries,
        movie.duration)
}

data class Genre(
    val id: Int,
    val name : String
)

data class Country(
    val iso_3166_1 : String,
    val name : String
)
