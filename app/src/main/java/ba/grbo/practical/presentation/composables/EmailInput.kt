package ba.grbo.practical.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.practical.R
import ba.grbo.practical.framework.theme.PracticalTheme

@Composable
fun EmailInput(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    Input(
        modifier = modifier,
        value = email,
        label = R.string.email_input_placeholder,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
        onValueChange = onEmailChange
    )
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
        EmailInput(email = "grbo.dev@gmail.com", onEmailChange = {})
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
            EmailInput(email = "", onEmailChange = {})
        }
    }
}