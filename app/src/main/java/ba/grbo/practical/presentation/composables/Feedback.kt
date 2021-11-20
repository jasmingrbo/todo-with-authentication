package ba.grbo.practical.presentation.composables

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.practical.framework.data.state.LoginState.Feedback
import ba.grbo.practical.framework.theme.PracticalTheme

@Composable
fun Feedback(
    modifier: Modifier = Modifier,
    feedback: Feedback
) {
    if (feedback.message.isNotEmpty()) Text(
        modifier = modifier,
        text = feedback.message,
        color = if (feedback.error) MaterialTheme.colors.error else Color.Green
    )
}

@Preview(
    name = "incorrect-credentials",
    showBackground = true
)
@Preview(
    name = "incorrect-credentials",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FeedbackIncorrectCredentialsPreview() {
    PracticalTheme {
        Surface {
            Feedback(
                feedback = Feedback(
                    message = "Email or password incorrect.",
                    error = true
                )
            )
        }
    }
}

@Preview(
    name = "successfully-signed-up",
    showBackground = true
)
@Preview(
    name = "successfully-signed-up",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun FeedbackSuccessfullySignedUpPreview() {
    PracticalTheme {
        Surface {
            Feedback(
                feedback = Feedback(
                    message = "Successfully signed up.",
                    error = false
                )
            )
        }
    }
}