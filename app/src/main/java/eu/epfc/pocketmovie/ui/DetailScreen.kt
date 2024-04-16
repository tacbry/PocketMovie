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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.epfc.pocketmovie.network.Movie


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailScreen(movieId : Int?){
    val mainViewModel : MainScreenViewModel = viewModel()
    Scaffold{
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxWidth()) {
//            if (movieId != null) {
//                ShowDetails(mainViewModel = mainViewModel, movieId )
//            }

        }
    }
}


@Composable
fun ShowDetails(mainViewModel: MainScreenViewModel,movieId : Int = 693134) {
    if (movieId != null) {
        mainViewModel.fetchMovieDetails(movieId)
    }
    Column {
        //image
        Text(text = mainViewModel.movieDetails.value!!.title, fontSize = 20.sp)
        Row {
            Text(text = mainViewModel.movieDetails.value!!.release_date)
            //Text(text = mainViewModel.movieDetails.value!!.genres!!.name)
            Text(text = mainViewModel.movieDetails.value!!.vote_average.toString())

        }
        //resume
        // bouton pocket
    }
}


