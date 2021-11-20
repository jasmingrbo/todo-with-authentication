package ba.grbo.practical.presentation.login

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.practical.R
import ba.grbo.practical.framework.data.state.Feedback
import ba.grbo.practical.framework.data.state.LoginState.Password
import ba.grbo.practical.framework.theme.PracticalTheme
import ba.grbo.practical.presentation.composables.CredentialScreen
import ba.grbo.practical.presentation.composables.EmailInput
import ba.grbo.practical.presentation.composables.Feedback
import ba.grbo.practical.presentation.composables.LoginButton
import ba.grbo.practical.presentation.composables.PasswordInput
import ba.grbo.practical.presentation.composables.RestorePassword
import ba.grbo.practical.presentation.composables.SignUp
import ba.grbo.practical.presentation.composables.ThirdPartyLoginButtons
import ba.grbo.practical.presentation.keyboardAsState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    email: String,
    password: Password,
    feedback: Feedback,
    onEmailChange: (String) -> Unit,
    onResetEmailButtonClicked: () -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPasswordTextClicked: () -> Unit,
    onPasswordVisibilityButtonClicked: () -> Unit,
    onResetPasswordButtonClicked: () -> Unit,
    onLoginButtonClicked: () -> Unit,
    onGoogleLoginButtonClicked: () -> Unit,
    onFacebookLoginButtonClicked: () -> Unit,
    onSignUpTextClicked: () -> Unit
) {
    CredentialScreen(modifier = modifier) {
        val keyboardOpened by keyboardAsState()
        val spacer by animateDpAsState(if (keyboardOpened) 12.dp else 24.dp)
        val specialSpacer by animateDpAsState(if (keyboardOpened) 18.dp else 36.dp)
        val fixedSpacer = 24.dp

        Feedback(feedback = feedback)

        Spacer(modifier = Modifier.height(spacer))

        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = R.string.login),
            fontSize = 36.sp
        )

        Spacer(modifier = Modifier.height(spacer))

        EmailInput(
            modifier = Modifier.fillMaxWidth(),
            email = email,
            onEmailChange = onEmailChange,
            onResetEmailButtonClicked = onResetEmailButtonClicked
        )

        Spacer(modifier = Modifier.height(spacer))

        PasswordInput(
            modifier = Modifier.fillMaxWidth(),
            password = password.value,
            masked = password.masked,
            imeAction = ImeAction.Done,
            onPasswordChange = onPasswordChange,
            onPasswordVisibilityButtonClicked = onPasswordVisibilityButtonClicked,
            onResetPasswordButtonClicked = onResetPasswordButtonClicked,
            onImeActionClicked = onLoginButtonClicked
        )

        Spacer(modifier = Modifier.height(spacer / 2))

        RestorePassword(
            modifier = Modifier.align(Alignment.End),
            onClick = onForgotPasswordTextClicked
        )

        Spacer(modifier = Modifier.height(fixedSpacer))

        LoginButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLoginButtonClicked
        )

        Spacer(modifier = Modifier.height(specialSpacer))

        ThirdPartyLoginButtons(
            modifier = Modifier.fillMaxWidth(),
            onGoogleLoginButtonClicked = onGoogleLoginButtonClicked,
            onFacebookLoginButtonClicked = onFacebookLoginButtonClicked
        )

        Spacer(modifier = Modifier.height(spacer))

        SignUp(onSignUpTextClicked = onSignUpTextClicked)
    }
}

@Preview(
    name = "preview",
    showBackground = true
)
@Preview(
    name = "preview",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LoginPreview() {
    PracticalTheme {
        Surface {
            LoginScreen(
                email = "grbo.dev@gmail.com",
                password = Password.DEFAULT,
                feedback = Feedback.DEFAULT,
                onEmailChange = {},
                onResetEmailButtonClicked = {},
                onPasswordChange = {},
                onForgotPasswordTextClicked = {},
                onPasswordVisibilityButtonClicked = {},
                onResetPasswordButtonClicked = {},
                onLoginButtonClicked = {},
                onGoogleLoginButtonClicked = {},
                onFacebookLoginButtonClicked = {},
                onSignUpTextClicked = {}
            )
        }
    }
}