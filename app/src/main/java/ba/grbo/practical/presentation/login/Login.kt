package ba.grbo.practical.presentation.login

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable

@Composable
fun Login(
    email: String,
    pw: String,
    feedback: String,
    onEmailChanged: (String) -> Unit,
    onPwChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onGoogleLoginClicked: () -> Unit,
    onFacebookLoginClicked: () -> Unit
) {
    OutlinedTextField(value = email, onValueChange = onEmailChanged)
}