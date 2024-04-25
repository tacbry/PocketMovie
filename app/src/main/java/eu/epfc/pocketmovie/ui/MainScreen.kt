package eu.epfc.pocketmovie.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import eu.epfc.pocketmovie.model.Repository
import eu.epfc.pocketmovie.network.Movie
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onClickItem: (Int) -> Unit){
    //var checked by remember { mutableStateOf(true) }
    val viewModelFactory = MainViewModelFactory(LocalContext.current.applicationContext)
    val mainViewModel : MainScreenViewModel = viewModel(factory = viewModelFactory)

    Scaffold{
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxWidth()) {
            ShowMovies(mainViewModel,onClickItem)
        }
    }
}

@Composable
fun ShowMovies(mainViewModel: MainScreenViewModel,onClickItem: (Int) -> Unit ){
    mainViewModel.fetchMovies()
    LazyColumn{
        items(mainViewModel.movies.value.size) { index ->
            MovieItem(mainViewModel.movies.value[index], onClickItem)
        }
    }
}
