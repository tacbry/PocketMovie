package eu.epfc.pocketmovie.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.epfc.pocketmovie.database.PocketItem
import eu.epfc.pocketmovie.model.Repository

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PocketScreen(onClickItem : (Int) -> Unit){
    val viewModelFactory = MainViewModelFactory(LocalContext.current.applicationContext)
    val mainViewModel : MainScreenViewModel = viewModel(factory = viewModelFactory)
    Scaffold{
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxWidth()) {
            //Text("This is the pocketScreen")
            ShowPocketMovies(mainViewModel = MainScreenViewModel(),onClickItem)
        }
    }
}

@Composable
fun ShowPocketMovies(mainViewModel: MainScreenViewModel,onClickItem: (Int) -> Unit ){
    mainViewModel.fetchPocket()
    LazyColumn{
        items(mainViewModel.pocketList.value.size) { index ->
            MovieItemPocket(mainViewModel.pocketList.value[index], onClickItem)
        }
    }
}