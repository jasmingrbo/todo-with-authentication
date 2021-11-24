package ba.grbo.practical.presentation.composables

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import ba.grbo.practical.framework.theme.PracticalTheme

@Composable
fun PracticalApp(authenticated: Boolean?, ) {
    PracticalTheme {
        Surface {
            PracticalScreen(authenticated = authenticated,)
        }
    }
}

// add previews