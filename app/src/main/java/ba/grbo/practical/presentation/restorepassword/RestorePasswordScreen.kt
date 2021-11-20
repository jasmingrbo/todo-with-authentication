package ba.grbo.practical.presentation.restorepassword

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.practical.R
import ba.grbo.practical.framework.data.state.Feedback
import ba.grbo.practical.presentation.composables.CredentialScreen
import ba.grbo.practical.presentation.composables.EmailInput
import ba.grbo.practical.presentation.composables.Feedback
import ba.grbo.practical.presentation.composables.RestorePasswordButton
import ba.grbo.practical.presentation.keyboardAsState

@Composable
fun RestorePasswordScreen(
    modifier: Modifier = Modifier,
    email: String,
    feedback: Feedback,
    onEmailChange: (String) -> Unit,
    onResetEmailButtonClicked: () -> Unit,
    onRestorePasswordClicked: () -> Unit
) {
    CredentialScreen(modifier = modifier) {
        val spacer = 24.dp

        Feedback(feedback = feedback)

        Spacer(modifier = Modifier.height(spacer))

        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = R.string.restore_password),
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(spacer))

        EmailInput(
            modifier = Modifier.fillMaxWidth(),
            email = email,
            onEmailChange = onEmailChange,
            onResetEmailButtonClicked = onResetEmailButtonClicked
        )

        Spacer(modifier = Modifier.height(spacer))

        RestorePasswordButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onRestorePasswordClicked
        )
    }
}