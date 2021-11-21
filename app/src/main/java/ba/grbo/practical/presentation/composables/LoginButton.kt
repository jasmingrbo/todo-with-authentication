package ba.grbo.practical.presentation.composables

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.practical.R
import ba.grbo.practical.framework.theme.PracticalTheme

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit
) {
    CustomButton(
        modifier = modifier,
        text = R.string.login,
        enabled = enabled,
        onClick = onClick
    )
}

@Preview(
    name = "login-button",
    showBackground = true
)
@Preview(
    name = "login-button",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun LoginButtonPreview() {
    PracticalTheme {
        Surface {
            LoginButton(enabled = true, onClick = {})
        }
    }
}