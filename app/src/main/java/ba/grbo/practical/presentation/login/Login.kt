package ba.grbo.practical.presentation.login

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.practical.R
import ba.grbo.practical.framework.data.state.LoginState.Feedback
import ba.grbo.practical.framework.theme.PracticalTheme
import ba.grbo.practical.presentation.composables.EmailInput
import ba.grbo.practical.presentation.composables.Feedback
import ba.grbo.practical.presentation.composables.LoginButton
import ba.grbo.practical.presentation.composables.PasswordInput
import ba.grbo.practical.presentation.composables.SignUp
import ba.grbo.practical.presentation.composables.ThirdPartyLogin
import ba.grbo.practical.presentation.keyboardAsState

@Composable
fun Login(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    passwordMasked: Boolean,
    feedback: Feedback,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityButtonClicked: () -> Unit,
    onLoginButtonClicked: () -> Unit,
    onGoogleLoginButtonClicked: () -> Unit,
    onFacebookLoginButtonClicked: () -> Unit,
    onSignUpTextClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val keyboardOpened by keyboardAsState()
        val spacer = 24.dp
        val specialSpacer by animateDpAsState(if (keyboardOpened) 18.dp else 36.dp)
        val specialSpacerTwo by animateDpAsState(if (keyboardOpened) 14.dp else 24.dp)

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
            onEmailChange = onEmailChange
        )

        Spacer(modifier = Modifier.height(spacer))

        PasswordInput(
            modifier = Modifier.fillMaxWidth(),
            password = password,
            masked = passwordMasked,
            onPasswordChange = onPasswordChange,
            onPasswordVisibilityButtonClicked = onPasswordVisibilityButtonClicked,
            onDoneImeActionClicked = onLoginButtonClicked
        )

        Spacer(modifier = Modifier.height(spacer))

        LoginButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLoginButtonClicked
        )

        Spacer(modifier = Modifier.height(specialSpacer))

        ThirdPartyLogin(
            modifier = Modifier.fillMaxWidth(),
            onGoogleLoginButtonClicked = onGoogleLoginButtonClicked,
            onFacebookLoginButtonClicked = onFacebookLoginButtonClicked
        )

        Spacer(modifier = Modifier.height(specialSpacerTwo))

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
            Login(
                email = "grbo.dev@gmail.com",
                password = "1234",
                passwordMasked = true,
                feedback = Feedback.DEFAULT,
                onEmailChange = {},
                onPasswordChange = {},
                onPasswordVisibilityButtonClicked = {},
                onLoginButtonClicked = {},
                onGoogleLoginButtonClicked = {},
                onFacebookLoginButtonClicked = {},
                onSignUpTextClicked = {}
            )
        }
    }
}