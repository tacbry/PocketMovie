package eu.epfc.pocketmovie.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import eu.epfc.pocketmovie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(){
    val viewModelFactory = MainViewModelFactory(LocalContext.current.applicationContext)
    val mainViewModel : MainScreenViewModel = viewModel(factory = viewModelFactory)
    val context = LocalContext.current

    val developerName = "Tacbry"
    val appVersion = "1.0"

    Scaffold {
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxWidth() ){
            Column(modifier= Modifier
                .fillMaxSize()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {
                Text(text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Bold,
                    fontSize = 40.sp
                )
                Row {
                    Text(text = "version : ")
                    Text(text = appVersion)
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(text = "Developped by :")
                Text(text = developerName)
                Spacer(modifier = Modifier.padding(32.dp))
                SendFeedbackButton(context)
            }
        }
    }
}

@Composable
private fun SendFeedbackButton(context: Context) {
    Button(
        onClick = { sendFeedbackEmail(context) },
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(Color.Green)
    ) {
        Text(text = "Send Feedback")
    }
}

private fun sendFeedbackEmail(context: Context) {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf("0503brmeganck@student.epfc.eu"))
        putExtra(Intent.EXTRA_SUBJECT, "PocketMovie Feedback")
        putExtra(Intent.EXTRA_TEXT, "Test")
    }

    context.startActivity(
        Intent.createChooser(
            emailIntent,
            context.getString(R.string.app_name)
        )
    )
}


