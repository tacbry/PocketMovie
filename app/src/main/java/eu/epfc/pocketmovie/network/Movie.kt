package eu.epfc.pocketmovie.network

import androidx.compose.runtime.MutableState
import coil.compose.AsyncImage

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
    val backdrop_path: String,
    val budget: Int = 0,
    val adult: Boolean = false,
    val origin_country: List<String>,
    val duration: Int = 0
    )


data class Genre(
    val id: Int,
    val name : String
)
