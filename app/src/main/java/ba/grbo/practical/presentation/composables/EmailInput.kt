package ba.grbo.practical.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.core.domain.Email
import ba.grbo.core.domain.Inputable
import ba.grbo.practical.R
import ba.grbo.practical.framework.mics.DEFAULT
import ba.grbo.practical.framework.theme.PracticalTheme

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    email: Inputable,
    enabled: Boolean,
    imeAction: ImeAction = ImeAction.Next,
    onEmailChange: (String) -> Unit,
    onResetEmailButtonClicked: () -> Unit,
    onImeActionButtonClicked: () -> Unit
) {
    Input(
        modifier = modifier,
        value = email.value,
        enabled = enabled,
        isError = email.isError,
        errorMessage = email.errorMessage,
        label = R.string.email_input_placeholder,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = { onImeActionButtonClicked() },
            onNext = { onImeActionButtonClicked() }
        ),
        onValueChange = onEmailChange
    ) {
        ResetButton(
            visible = email.value.isNotEmpty(),
            enabled = enabled,
            onClick = onResetEmailButtonClicked
        )
    }
}

@Preview(
    name = "nonempty",
    showBackground = true
)
@Preview(
    name = "nonempty",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun EmailInputNonEmptyPreview() {
    PracticalTheme {
        EmailInput(
            email = Email(
                value = "grbo.dev@gmail.com",
                isError = false,
                errorMessage = Int.DEFAULT
            ),
            enabled = true,
            onEmailChange = {},
            onResetEmailButtonClicked = {},
            onImeActionButtonClicked = {}
        )
    }
}

@Preview(
    name = "empty",
    showBackground = true
)
@Preview(
    name = "empty",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun EmailInputEmptyPreview() {
    PracticalTheme {
        Surface {
            EmailInput(
                email = Email(
                    value = String.DEFAULT,
                    isError = true,
                    errorMessage = R.string.email_empty
                ),
                enabled = true,
                onEmailChange = {},
                onResetEmailButtonClicked = {},
                onImeActionButtonClicked = {}
            )
        }
    }
}