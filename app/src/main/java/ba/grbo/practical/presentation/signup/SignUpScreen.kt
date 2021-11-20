package ba.grbo.practical.presentation.signup

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.practical.R
import ba.grbo.practical.framework.data.state.Feedback
import ba.grbo.practical.framework.data.state.SignUpState.Password
import ba.grbo.practical.presentation.composables.CredentialScreen
import ba.grbo.practical.presentation.composables.EmailInput
import ba.grbo.practical.presentation.composables.Feedback
import ba.grbo.practical.presentation.composables.PasswordInput
import ba.grbo.practical.presentation.composables.SignUpButton
import ba.grbo.practical.presentation.composables.ThirdPartySignUpButtons
import ba.grbo.practical.presentation.keyboardAsState

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    email: String,
    password: Password,
    feedback: Feedback,
    onEmailChange: (String) -> Unit,
    onResetEmailButtonClicked: () -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityButtonClicked: () -> Unit,
    onResetPasswordButtonClicked: () -> Unit,
    onRepeatedPasswordChange: (String) -> Unit,
    onRepeatedPasswordVisibilityButtonClicked: () -> Unit,
    onResetRepeatedPasswordButtonClicked: () -> Unit,
    onGoogleSignUpButtonClicked: () -> Unit,
    onFacebookSignUpButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit,
) {
    CredentialScreen(modifier = modifier) {
        val keyboardOpened by keyboardAsState()
        val spacer by animateDpAsState(if (keyboardOpened) 16.dp else 24.dp)

        Feedback(feedback = feedback)

        Spacer(modifier = Modifier.height(spacer))

        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = R.string.sign_up),
            fontSize = 36.sp
        )

        Spacer(modifier = Modifier.height(spacer))

        ThirdPartySignUpButtons(
            modifier = Modifier.fillMaxWidth(),
            onGoogleSignUpButtonClicked = onGoogleSignUpButtonClicked,
            onFacebookSignUpButtonClicked = onFacebookSignUpButtonClicked
        )

        Spacer(modifier = Modifier.height(spacer))

        val focusManager = LocalFocusManager.current
        EmailInput(
            modifier = Modifier.fillMaxWidth(),
            email = email,
            onEmailChange = onEmailChange,
            onResetEmailButtonClicked = onResetEmailButtonClicked,
            onImeActionButtonClicked = { focusManager.moveFocus(FocusDirection.Down) }
        )

        Spacer(modifier = Modifier.height(spacer))


        PasswordInput(
            modifier = Modifier.fillMaxWidth(),
            password = password.value,
            masked = password.masked,
            imeAction = ImeAction.Next,
            onPasswordChange = onPasswordChange,
            onPasswordVisibilityButtonClicked = onPasswordVisibilityButtonClicked,
            onResetPasswordButtonClicked = onResetPasswordButtonClicked,
            onImeActionButtonClicked = { focusManager.moveFocus(FocusDirection.Down) }
        )

        Spacer(modifier = Modifier.height(spacer))

        PasswordInput(
            modifier = Modifier.fillMaxWidth(),
            password = password.repeated,
            masked = password.repeatedMasked,
            imeAction = ImeAction.Done,
            label = R.string.repeat_password_input_placeholder,
            onPasswordChange = onRepeatedPasswordChange,
            onPasswordVisibilityButtonClicked = onRepeatedPasswordVisibilityButtonClicked,
            onResetPasswordButtonClicked = onResetRepeatedPasswordButtonClicked,
            onImeActionButtonClicked = onSignUpButtonClicked
        )

        Spacer(modifier = Modifier.height(spacer))

        SignUpButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSignUpButtonClicked
        )
    }
}