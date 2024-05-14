package eu.epfc.pocketmovie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.epfc.pocketmovie.model.Repository
import eu.epfc.pocketmovie.model.Repository.movies


@ExperimentalMaterial3Api
@Composable
fun MainScreen(onClickItem: (Int) -> Unit){
    val viewModelFactory = MainViewModelFactory(LocalContext.current.applicationContext)
    val mainViewModel : MainScreenViewModel = viewModel(factory = viewModelFactory)

// launch effect ici avec une variable mutable qui est changée dans le catch (ca crée une recomposition) (pour toast)

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
    LazyColumn{
        items(mainViewModel.movies.value.size+1) { index ->
            if (index < mainViewModel.movies.value.size){
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
