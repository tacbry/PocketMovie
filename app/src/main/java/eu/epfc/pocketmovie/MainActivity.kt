package eu.epfc.pocketmovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import eu.epfc.pocketmovie.model.Repository
import eu.epfc.pocketmovie.ui.MainScreen
import eu.epfc.pocketmovie.ui.MainScreenViewModel
import eu.epfc.pocketmovie.ui.PocketApp
import eu.epfc.pocketmovie.ui.theme.PocketMovieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Repository.initDatabase(applicationContext)

        setContent {
            PocketMovieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PocketApp(MainScreenViewModel())

                }
            }
        }
    }
}

