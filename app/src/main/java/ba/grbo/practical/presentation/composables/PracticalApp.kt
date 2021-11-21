package ba.grbo.practical.presentation.composables

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import ba.grbo.practical.framework.theme.PracticalTheme
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PracticalApp(auth: FirebaseAuth) {
    PracticalTheme {
        Surface {
            PracticalNavHost(auth = auth)
        }
    }
}

// add previews