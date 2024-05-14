package eu.epfc.pocketmovie.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.epfc.pocketmovie.model.Repository.CountryFlag
import eu.epfc.pocketmovie.model.Repository.ViewPosterDetailCoil
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
                LaunchedEffect(true) {
                    mainViewModel.fetchMovieDetails(movieId)
                }
                ItemDetails(viewModel = mainViewModel, movieId ,)
            }
        }
    }
}

/*---------------------- Configuration de la disposition ----------------------*/
@Composable
fun ItemDetails(viewModel: MainScreenViewModel, movieId: Int) {
    LaunchedEffect(true){
        viewModel.fetchMovieDetails(movieId)
    }
    val checkedState = remember { viewModel.switchIsOn }
    val movieDetails = viewModel.movieDetails.value
    val scrollState = rememberScrollState()
    if (movieDetails != null) {

    Column {
        ViewPosterDetailCoil(poster = movieDetails.backdrop_path)
        Column(modifier = Modifier.padding(16.dp).verticalScroll(scrollState)) {
            Text(text = movieDetails.title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp))
            Row {
                Text(text = movieDetails.release_date,modifier = Modifier.padding(bottom = 4.dp))
                Text(" | ")
                Text(text = ((movieDetails.vote_average * 10.0).roundToInt() / 10.0).toString())
                Text(" | ")
                CountryFlag(codePays = movieDetails.origin_country[0])

            }
            Text(text = viewModel.mergeGenre(movieDetails.genres),modifier = Modifier.padding(bottom = 4.dp))
            Text("Overview",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 4.dp))
            Text(movieDetails.overview)
            Spacer(modifier = Modifier.padding(16.dp))

            Row(modifier = Modifier
                .align(Alignment.End)
                .padding(6.dp)) {
                Text(text = "In the pocket",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(6.dp))

                Switch(
                    checked = checkedState.value,
                    onCheckedChange = {
                        checkedState.value = it
                        viewModel.switchChange(it,movieId)
                    },
                    modifier = Modifier.align(Alignment.Bottom)
                    )
                }
            }
        }
    }
}


