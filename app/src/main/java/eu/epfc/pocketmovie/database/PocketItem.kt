package eu.epfc.pocketmovie.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.epfc.pocketmovie.network.Genre
import eu.epfc.pocketmovie.network.Movie
import eu.epfc.pocketmovie.ui.mergeCountry

@Entity(tableName =  "PocketItem")
data class PocketItem(
    @PrimaryKey(autoGenerate=true)
    val id: Int = 0,
    @ColumnInfo(name = "movieId")
    val movieId: Int,
    @ColumnInfo(name = "Title")
    val title: String,
    @ColumnInfo(name = "release_date")
    val release_date: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "vote_average")
    val vote_average: Double,
    @ColumnInfo(name = "genres")
    val genres: String,
    @ColumnInfo(name = "original_language")
    val original_language: String,
    @ColumnInfo(name = "pocket")
    var pocket: Boolean = false,
    @ColumnInfo(name = "poster_path")
    var poster_path: String,
    @ColumnInfo(name = "backdrop_path")
    val backdrop_path: String,
    @ColumnInfo(name = "budget")
    val budget: Int = 0,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "origin_country")
    val origin_country: String,
    @ColumnInfo(name = "duration")
    val duration: Int = 0
){
    constructor(movie : Movie) : this(
        id = 0,
        movieId = movie.id,
        title= movie.title,
        release_date = movie.release_date,
        overview = movie.overview,
        vote_average = movie.vote_average,
        genres = mergeGenre(movie.genres),
        original_language = movie.original_language,
        pocket = movie.pocket,
        poster_path = movie.poster_path,
        backdrop_path = movie.backdrop_path,
        budget = movie.budget,
        adult = movie.adult,
        origin_country = mergeCountry(movie.origin_country) ,
        duration = movie.duration)
}

data class Genre(
    val id: Int,
    val name : String
)




data class Country(
    val iso_3166_1 : String,
    val name : String
)
fun mergeGenre(genres: List<Genre>): String {
    return genres.joinToString(separator = ", ") { it.name }
}



