package eu.epfc.pocketmovie.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailScreen(movieId : Int?){
    Scaffold{
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxWidth()) {
            ShowDetails(mainViewModel = )

        }
    }
}

@Composable
fun ShowDetails(mainViewModel: MainScreenViewModel) {
    if (movieId != null) {
        mainViewModel.fetchMovieDetails(movieId)
    }
    Column {
        //image
        //title
        Row {
            //date
            //style
            //note
        }
        //resume
        // bouton pocket
    }
}


