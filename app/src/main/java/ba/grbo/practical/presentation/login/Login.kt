package ba.grbo.practical.presentation.login

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable

@Composable
fun Login(
    email: String,
    password: String,
    feedback: String,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onGoogleLoginClicked: () -> Unit,
    onFacebookLoginClicked: () -> Unit
) {
    TextField(value = email, onValueChange = onEmailChanged)
}