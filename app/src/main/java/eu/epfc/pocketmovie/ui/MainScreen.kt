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
    val mainScreenViewModel : MainScreenViewModel = viewModel()
    val navController = rememberNavController()
    val viewModelFactory = MainViewModelFactory(LocalContext.current.applicationContext)
    val mainViewModel : MainScreenViewModel = viewModel(factory = viewModelFactory)
    val context = LocalContext.current.applicationContext

    Scaffold{
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxWidth()) {
            ShowMovies(mainViewModel,onClickItem)
            Button(onClick = { Repository.pageNumber+=1}) {
                Text("Next page")
            }
        }
    }
/*    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        }
    )//checked = pocket*/
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


@Composable
fun MovieItem(movie: Movie, onClickItem: (Int) -> Unit){
    val roundoff = (movie.vote_average * 10.0).roundToInt() / 10.0
    Column {
        Card(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = { onClickItem(movie.id)}))
        {
            Row {
                ViewPosterCoil(movie.poster_path)
                Column {
                    Text(text = movie.title, fontSize = 20.sp)
                    Text(text = "Release Date :" + movie.release_date)
                    Text(text = "Rating :$roundoff")
                    //LanguageFlag(codePays = movie.originalLanguage)
                }
            }
        }

        //Text(text = movie.overview)
        //Spacer(Modifier.padding(15.dp))
    }
}



@Composable
fun ViewPosterCoil(poster: String) {
    val url = "https://image.tmdb.org/t/p/w500$poster"
    AsyncImage(model = url, contentDescription = "poster",
    modifier = Modifier.size(width = 100.dp, height = 200.dp) )
}



@Composable

fun LanguageFlag(codePays: String) {
    var code = codePays
    if (code=="en"){
        code = "gb"
    }
        val url = "https://flagcdn.com/h20/$code.png"
    AsyncImage(model = url, contentDescription = "languageflag")
}

/*


@Composable
fun ClickNextPage(mainViewModel: MainScreenViewModel){
    Repository.pageNumber+=1
    ShowMovies(mainViewModel)
}

@Composable
fun ClickPreviousPage(mainViewModel: MainScreenViewModel){
    Repository.pageNumber-=1
    ShowMovies(mainViewModel)
}
*/