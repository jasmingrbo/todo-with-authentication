package ba.grbo.practical.presentation.screens.restorepassword

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.core.domain.Email
import ba.grbo.practical.R
import ba.grbo.practical.presentation.composables.CredentialScreen
import ba.grbo.practical.presentation.composables.EmailInput
import ba.grbo.practical.presentation.composables.RestorePasswordButton

@Composable
fun RestorePasswordScreen(
    modifier: Modifier = Modifier,
    email: Email,
    onEmailChange: (String) -> Unit,
    onResetEmailButtonClicked: () -> Unit,
    onRestorePasswordClicked: () -> Unit
) {
    CredentialScreen(modifier = modifier) {
        val spacer = 24.dp

        Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = R.string.restore_password),
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(spacer))

        EmailInput(
            modifier = Modifier.fillMaxWidth(),
            email = email,
            imeAction = ImeAction.Done,
            onEmailChange = onEmailChange,
            onResetEmailButtonClicked = onResetEmailButtonClicked,
            onImeActionButtonClicked = onRestorePasswordClicked
        )

        Spacer(modifier = Modifier.height(spacer))

        RestorePasswordButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onRestorePasswordClicked
        )

        Spacer(modifier = Modifier.height(spacer / 2))
    }
}