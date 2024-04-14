package eu.epfc.pocketmovie.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.squareup.picasso.Picasso
import eu.epfc.pocketmovie.R
import eu.epfc.pocketmovie.model.Repository
import eu.epfc.pocketmovie.network.Movie
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainScreen(){
    //var checked by remember { mutableStateOf(true) }
    val mainScreenViewModel : MainScreenViewModel = viewModel()
    val navController = rememberNavController()
    val viewModelFactory = MainViewModelFactory(LocalContext.current.applicationContext)
    val mainViewModel : MainScreenViewModel = viewModel(factory = viewModelFactory)
    val context = LocalContext.current.applicationContext

    Scaffold(
        topBar = {
            MovieTopAppBar(
                canNavigateBack = true,
                navigateUp = { /*TODO*/ }
            )
        },
        bottomBar = {
            MovieBottomAppBar(
                canNavigateBack = true,
                navigateUp = { /*TODO*/ }
        )
        }
    ) {
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxWidth()) {
                /*LazyColumn{
                    items(mainViewModel.movies.value.size) { index ->
                        MovieItem(mainViewModel.movies.value[index])
                    }
                }*/
            //Repository.pageNumber = 5
            ShowMovies(mainViewModel)

            if (Repository.pageNumber==1){
                Button(onClick = { Repository.pageNumber+=1}) {
                Text("Next page")
            }
            }
            if (Repository.pageNumber>1){

                Row {
                    Button(onClick = { Repository.pageNumber-=1}) {
                        Text("Previous page")
                    }

                    Button(onClick = { Repository.pageNumber+=1}) {
                        Text("Next page")
                    }
                }


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
@OptIn(ExperimentalMaterial3Api::class)
fun MovieTopAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier)
{
    TopAppBar(
        title = {
            Text(stringResource(id = R.string.app_name),fontSize = 30.sp)
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "more"
                )
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "info"
                )
            }
        }
    )
}

@Composable
fun MovieBottomAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier)
{
   BottomAppBar(
       actions = {
           Row(
               modifier = Modifier.fillMaxWidth(),
               horizontalArrangement = Arrangement.SpaceAround,
               verticalAlignment = Alignment.CenterVertically
           ) {
               Column(horizontalAlignment = Alignment.CenterHorizontally) {
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(
                           imageVector = Icons.Filled.PlayArrow,
                           contentDescription = "recent movies"
                       )
                   }
                   Text(text = "Recent Movies")
               }

               Column(horizontalAlignment = Alignment.CenterHorizontally) {
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(imageVector = Icons.Filled.Star, contentDescription = "pocket")
                   }
                   Text(text = "Pocket")
               }
           }
       }
   )
}


@Composable
fun MovieItem(movie: Movie){
    val roundoff = (movie.vote_average * 10.0).roundToInt() / 10.0
    Column {
        Card(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(onClick = { }))
        {
            ViewPosterCoil(movie.poster_path)
            Text(text = movie.title, fontSize = 20.sp)
            Text(text = "Release Date :" + movie.release_date)
            Text(text = "Rating :$roundoff")
        }

        //Text(text = movie.original_language)

        //LanguageFlag(codePays = movie.original_language, ImageView)
        AfficherDrapeauAvecCoil(codePays = movie.original_language)

        //Text(text = movie.overview)
        //Spacer(Modifier.padding(15.dp))
    }


}

fun LanguageFlag(codePays: String, imageView: ImageView) {
   try {
       val url = "https://flagcdn.com/h20/$codePays.png"
       Picasso.get()
           .load(url)
           .into(imageView)
   } catch (e : Exception){
       val url = "https://flagcdn.com/h20/fr.png"
       Picasso.get()
           .load(url)
           .into(imageView)
   }
}

fun ViewPoster(poster: String, imageView: ImageView) {
        val url = "https://image.tmdb.org/t/p/w500$poster"
        Picasso.get()
            .load(url)
            .into(imageView)
    }

@Composable
fun ViewPosterCoil(poster: String) {
    val url = "https://image.tmdb.org/t/p/w500$poster"
    val painter = rememberImagePainter(data = url)
    Image(painter = painter, contentDescription = null)
}

@Composable
fun AfficherDrapeauAvecCoil(codePays: String) {
    var code = codePays
    if (code=="en"){
        code = "gb"
    }
        val url = "https://flagcdn.com/h20/$code.png"
        val painter = rememberImagePainter(data = url)
        Image(painter = painter, contentDescription = null)
}

@Composable
fun ShowMovies(mainViewModel: MainScreenViewModel){
    LazyColumn{
        items(mainViewModel.movies.value.size) { index ->
            MovieItem(mainViewModel.movies.value[index])
        }
    }
}

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