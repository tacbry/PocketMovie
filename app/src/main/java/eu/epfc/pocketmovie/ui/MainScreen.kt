package eu.epfc.pocketmovie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.epfc.pocketmovie.model.Repository


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(onClickItem: (Int) -> Unit){
    var checked by remember { mutableStateOf(true) }
    val viewModelFactory = MainViewModelFactory(LocalContext.current.applicationContext)
    val mainViewModel : MainScreenViewModel = viewModel(factory = viewModelFactory)

    Scaffold{
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxWidth()) {
            Column {
                ShowMovies(mainViewModel,onClickItem)
            }
        }
    }
}

@Composable
fun ShowMovies(mainViewModel: MainScreenViewModel,onClickItem: (Int) -> Unit ){
    mainViewModel.fetchMovies()
    LazyColumn{
        items(mainViewModel.movies.value.size+1) { index ->
            if (index < mainViewModel.movies.value.size){
                //Divider(color = Color.LightGray, thickness = 1.dp)
                MovieItem(mainViewModel.movies.value[index], onClickItem)
            } else{
                Box(modifier = Modifier
                    .clickable {
                        Repository.pageNumber += 1
                        mainViewModel.fetchMovies()
                    }
                    .fillMaxWidth()
                    .height(128.dp)
                    .background(Color.DarkGray), contentAlignment = Alignment.Center){
                    Text(text = "Show more")
                }


            }
        }
    }
}
