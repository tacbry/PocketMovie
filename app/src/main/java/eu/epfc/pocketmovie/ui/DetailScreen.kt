package eu.epfc.pocketmovie.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.epfc.pocketmovie.network.Movie


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
                ShowDetails(mainViewModel = mainViewModel, movieId )
            }

        }
    }
}



@Composable
fun ShowDetails(mainViewModel: MainScreenViewModel, movieId: Int){
    mainViewModel.fetchMovieDetails(movieId)
    mainViewModel.movieDetails.value?.let { ItemDetails(movie = it) }
}
@Composable
fun ItemDetails(movie : Movie) {
    Column {
        ViewPosterCoil(poster = movie.poster_path)
        Text(text = movie.title, fontSize = 20.sp)
        Row {
            Text(text = movie.release_date)
            //Text(text = mainViewModel.movieDetails.value!!.genres!!.name)
            Text(text = movie.vote_average.toString())
            CountryFlag(codePays = movie.production_countries.iso_3166_1)

            //resume
            // bouton pocket
        }
    }
}


