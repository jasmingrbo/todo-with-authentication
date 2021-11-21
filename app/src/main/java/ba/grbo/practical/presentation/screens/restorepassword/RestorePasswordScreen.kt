package ba.grbo.practical.presentation.screens.restorepassword

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ba.grbo.core.domain.Email
import ba.grbo.practical.R
import ba.grbo.practical.presentation.composables.CredentialScreen
import ba.grbo.practical.presentation.composables.EmailInput
import ba.grbo.practical.presentation.composables.Header
import ba.grbo.practical.presentation.composables.RestorePasswordButton

@Composable
fun RestorePasswordScreen(
    modifier: Modifier = Modifier,
    email: Email,
    loading: Boolean,
    @StringRes feedback: Int,
    onEmailChange: (String) -> Unit,
    onResetEmailButtonClicked: () -> Unit,
    onRestorePasswordClicked: () -> Unit
) {
    CredentialScreen(
        modifier = modifier,
        loading = loading,
    ) {
        val spacer = 24.dp

        Header(
            modifier = Modifier.fillMaxWidth(),
            header = R.string.restore_password,
            feedback = feedback,
            enabled = !loading
        )

        Spacer(modifier = Modifier.height(spacer))

        EmailInput(
            modifier = Modifier.fillMaxWidth(),
            email = email,
            enabled = !loading,
            imeAction = ImeAction.Done,
            onEmailChange = onEmailChange,
            onResetEmailButtonClicked = onResetEmailButtonClicked,
            onImeActionButtonClicked = onRestorePasswordClicked
        )

        Spacer(modifier = Modifier.height(spacer))

        RestorePasswordButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading,
            onClick = onRestorePasswordClicked
        )

        Spacer(modifier = Modifier.height(spacer / 2))
    }
}