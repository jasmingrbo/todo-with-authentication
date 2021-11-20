package ba.grbo.practical.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.practical.R
import ba.grbo.practical.framework.theme.PracticalTheme

@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    password: String,
    masked: Boolean,
    onPasswordChange: (String) -> Unit,
    onPasswordVisibilityButtonClicked: () -> Unit,
    onDoneImeActionClicked: () -> Unit
) {
    Input(
        modifier = modifier,
        value = password,
        label = R.string.password_input_placeholder,
        visualTransformation = if (masked) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onDoneImeActionClicked() }
        ),
        onValueChange = onPasswordChange
    ) {
        PasswordVisibilityButton(
            visible = !masked,
            onClick = onPasswordVisibilityButtonClicked
        )
    }
}

@Composable
fun PasswordVisibilityButton(
    visible: Boolean,
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(
                if (!visible) R.drawable.ic_visibility
                else R.drawable.ic_visibility_off
            ),
            contentDescription = stringResource(R.string.password_trailing_icon_description)
        )
    }
}

@Preview(
    name = "visible",
    showBackground = true
)
@Preview(
    name = "visible",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PasswordVisibilityButtonVisiblePreview() {
    PracticalTheme {
        Surface {
            PasswordVisibilityButton(visible = true, onClick = {})
        }
    }
}

@Preview(
    name = "invisible",
    showBackground = true
)
@Preview(
    name = "invisible",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PasswordVisibilityButtonInvisiblePreview() {
    PracticalTheme {
        Surface {
            PasswordVisibilityButton(visible = false, onClick = {})
        }
    }
}

@Preview(
    name = "empty-masked",
    showBackground = true
)
@Preview(
    name = "empty-masked",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PasswordInputEmptyMaskedPreview() {
    PracticalTheme {
        Surface {
            PasswordInput(
                password = "",
                masked = true,
                onPasswordChange = {},
                onPasswordVisibilityButtonClicked = {},
                onDoneImeActionClicked = {}
            )
        }
    }
}

@Preview(
    name = "empty-unmasked",
    showBackground = true
)
@Preview(
    name = "empty-unmasked",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PasswordInputEmptyUnmaskedPreview() {
    PracticalTheme {
        Surface {
            PasswordInput(
                password = "",
                masked = false,
                onPasswordChange = {},
                onPasswordVisibilityButtonClicked = {},
                onDoneImeActionClicked = {}
            )
        }
    }
}

@Preview(
    name = "nonempty-masked",
    showBackground = true
)
@Preview(
    name = "nonempty-masked",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PasswordInputNonEmptyMaskedPreview() {
    PracticalTheme {
        Surface {
            PasswordInput(
                password = "12345",
                masked = true,
                onPasswordChange = {},
                onPasswordVisibilityButtonClicked = {},
                onDoneImeActionClicked = {}
            )
        }
    }
}

@Preview(
    name = "nonempty-unmasked",
    showBackground = true
)
@Preview(
    name = "nonempty-unmasked",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PasswordInputNonEmptyUnmaskedPreview() {
    PracticalTheme {
        Surface {
            PasswordInput(
                password = "12345",
                masked = false,
                onPasswordChange = {},
                onPasswordVisibilityButtonClicked = {},
                onDoneImeActionClicked = {}
            )
        }
    }
}

