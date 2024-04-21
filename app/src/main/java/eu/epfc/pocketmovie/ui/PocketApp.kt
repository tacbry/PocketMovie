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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment
import eu.epfc.pocketmovie.R

enum class MovieState() {
    MAINSCREEN,
    POCKET,
    DETAIL
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocketApp(){
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            MovieTopAppBar(
                canNavigateBack = false,
                navigateUp = { /* TODO: implement back navigation */ }
            )
        },
        bottomBar = {
            MovieBottomAppBar(
                canNavigateBack = false,
                navigateUp = { /*TODO*/ })
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MovieState.MAINSCREEN.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = MovieState.MAINSCREEN.name){
                MainScreen(onClickItem = {movieId -> navController.navigate(MovieState.DETAIL.name + "/$movieId")})
            }
            composable(route = MovieState.POCKET.name){

            }
            composable(route = "${MovieState.DETAIL.name}/{movieId}" ){
                val detailMovieId = it.arguments?.getString("movieId")?.toInt()
                DetailScreen(detailMovieId ?: 0)
            }
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MovieTopAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
)
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
                    IconButton(onClick = { /*goto Mainscrenn */}) {
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