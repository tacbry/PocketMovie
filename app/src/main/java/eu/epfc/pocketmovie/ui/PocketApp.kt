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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.epfc.pocketmovie.R

enum class MovieState() {
    MAINSCREEN,
    POCKET,
    DETAIL,
    ABOUT
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocketApp(){
    val navController = rememberNavController()
    Scaffold(
        topBar = { MovieTopAppBar(navController) },
        bottomBar = { MovieBottomAppBar(navController) }
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
                PocketScreen(onClickItem = {movieId -> navController.navigate(MovieState.DETAIL.name + "/$movieId")})
            }
            composable(route = "${MovieState.DETAIL.name}/{movieId}" ){
                val detailMovieId = it.arguments?.getString("movieId")?.toInt()
                DetailScreen(detailMovieId ?: 0)
            }
            composable(route = MovieState.ABOUT.name){
                AboutScreen()
            }
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MovieTopAppBar(navController: NavController)
{
    TopAppBar(
        title = {
            Text(stringResource(id = R.string.app_name),fontSize = 30.sp)
        },
        actions = {
//            IconButton(onClick = { /* do something */ }) {
//                Icon(
//                    imageVector = Icons.Filled.Search,
//                    contentDescription = "more"
//                )
//            }
            IconButton(onClick = { navController.navigate(MovieState.ABOUT.name)}) {
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
    navController: NavController
)
{
    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = { navController.navigate(MovieState.MAINSCREEN.name)}) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "recent movies"
                        )
                    }
                    Text(text = "Recent Movies")
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    IconButton(onClick = { navController.navigate(MovieState.POCKET.name) }) {
                        Icon(imageVector = Icons.Filled.Star, contentDescription = "pocket")
                    }
                    Text(text = "Pocket")
                }
            }
        }
    )
}