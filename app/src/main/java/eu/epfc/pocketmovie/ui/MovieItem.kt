package eu.epfc.pocketmovie.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.epfc.pocketmovie.database.PocketItem
import eu.epfc.pocketmovie.network.Movie
import kotlin.math.roundToInt

@Composable
fun MovieItem(movie: Movie, onClickItem: (Int) -> Unit){
    Column {
        Card(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = { onClickItem(movie.id) }))
        {
            Row {
                ViewPosterCoil(movie.poster_path)
                Column {
                    Text(text = movie.title, fontSize = 20.sp)
                    Text(text = "Release Date :" + movie.release_date)
                    Text(text = "Rating :${(movie.vote_average * 10.0).roundToInt() / 10.0}")
                    CountryFlag(codePays = movie.original_language)
                }
            }
        }
    }
}

@Composable
fun MovieItemPocket(pocket: PocketItem, onClickItem: (Int) -> Unit){
    Column {
        Card(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = { onClickItem(pocket.movieId) }))
        {
            Row {
                ViewPosterCoil(pocket.poster_path)
                Column {
                    Text(text = pocket.title, fontSize = 20.sp)
                    Text(text = "Release Date :" + pocket.release_date)
                    Text(text = "Rating :${(pocket.vote_average * 10.0).roundToInt() / 10.0}")
                    CountryFlag(codePays = pocket.original_language)
                }
            }
        }
    }
}


