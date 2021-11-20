package ba.grbo.practical.presentation.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ba.grbo.practical.R
import ba.grbo.practical.framework.theme.PracticalTheme

@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    CustomButton(
        modifier = modifier,
        text = R.string.login,
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
            LoginButton(onClick = {})
        }
    }
}