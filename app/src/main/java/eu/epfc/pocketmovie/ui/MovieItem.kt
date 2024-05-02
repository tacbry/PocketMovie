package eu.epfc.pocketmovie.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import eu.epfc.pocketmovie.database.PocketItem
import eu.epfc.pocketmovie.network.Movie
import kotlin.math.roundToInt

@Composable
fun MovieItem(movie: Movie, onClickItem: (Int) -> Unit){
    Column {
            Row(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clickable(onClick = { onClickItem(movie.id) })) {
                ViewPosterCoil(movie.poster_path)
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxHeight()) {
                    Text(text = movie.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp))
                    Text(text = "Release Date: ${movie.release_date}",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 4.dp) )
                    Text(text = "Rating: ${(movie.vote_average * 10.0).roundToInt() / 10.0}",
                        fontSize = 14.sp)

                    //CountryFlag(codePays = movie.origin_country.let { mergeCountry(it) })
                }
            }
    }
}

@Composable
fun MovieItemPocket(pocket: PocketItem, onClickItem: (Int) -> Unit){
    Column {
        Row(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = { onClickItem(pocket.movieId) })) {
            ViewPosterCoil(pocket.poster_path)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight()) {
                Text(text = pocket.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp))
                Text(text = "Release Date: ${pocket.release_date}",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 4.dp) )
                Text(text = "Rating: ${(pocket.vote_average * 10.0).roundToInt() / 10.0}",
                    fontSize = 14.sp)
            }
        }
    }
}


