package ba.grbo.practical.presentation.composables

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import ba.grbo.practical.R
import ba.grbo.practical.framework.data.state.Feedback
import ba.grbo.practical.framework.mics.DEFAULT
import ba.grbo.practical.framework.theme.PracticalTheme

@Composable
fun Feedback(
    modifier: Modifier = Modifier,
    feedback: Feedback
) {
    if (feedback.message != Int.DEFAULT) Text(
        modifier = modifier,
        text = stringResource(feedback.message),
        textAlign = TextAlign.Center,
        color = if (feedback.error) MaterialTheme.colors.error else MaterialTheme.colors.secondary
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
                    message = R.string.incorrect_credentials,
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
                    message = R.string.successfully_signed_up,
                    error = false
                )
            )
        }
    }
}