package ba.grbo.practical.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import ba.grbo.practical.presentation.composables.PracticalApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PracticalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<PracticalViewModel>()
            PracticalApp(loggedIn = viewModel.loggedIn)
        }
    }
}