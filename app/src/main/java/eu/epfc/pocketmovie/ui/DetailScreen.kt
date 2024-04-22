package eu.epfc.pocketmovie.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.epfc.pocketmovie.network.Movie
import kotlin.math.roundToInt


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailScreen(movieId : Int?){
    val viewModelFactory = MainViewModelFactory(LocalContext.current.applicationContext)
    val mainViewModel : MainScreenViewModel = viewModel(factory = viewModelFactory)
    Scaffold{
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxWidth()) {
            if (movieId != null) {
                ItemDetails(viewModel = mainViewModel, movieId )
            }

        }
    }
}


@Composable
fun ItemDetails(viewModel: MainScreenViewModel, movieId: Int) {
    viewModel.fetchMovieDetails(movieId)
    val checkedState = remember { mutableStateOf(false) }
    val movieDetails = viewModel.movieDetails.value
    if (movieDetails != null) {
    Column {
        ViewPosterCoil(poster = movieDetails.backdrop_path)
        Text(text = movieDetails.title, fontSize = 20.sp)
        Row {
            Text(text = movieDetails.release_date)
            //Text(text = mainViewModel.movieDetails.value!!.genres!!.name)
            Text(text = ((movieDetails.vote_average * 10.0).roundToInt() / 10.0).toString())

            //    CountryFlag(codePays = movieDetails.production_countries.iso_3166_1[0])

            }
        Text("Overview")
        Text(movieDetails.overview)
        Spacer(modifier = Modifier.padding(16.dp))
        Row {
            Text(text = "In the pocket",)

            Switch(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it
                    viewModel.switchChange(it,movieId)
                }
            )
        }

    }
    }
}



