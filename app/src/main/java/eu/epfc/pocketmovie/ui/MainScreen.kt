package eu.epfc.pocketmovie.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import eu.epfc.pocketmovie.R
import eu.epfc.pocketmovie.network.Movie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun MainScreen(){
    //var checked by remember { mutableStateOf(true) }
    val mainScreenViewModel : MainScreenViewModel = viewModel()
    val navController = rememberNavController()
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



/*                LazyColumn(){
                    items(MainScreenViewModel.Movie.value.size) { index ->
                        MovieItem(MainScreenViewModel.characters.value[index])
                    }
                }*/
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

}

