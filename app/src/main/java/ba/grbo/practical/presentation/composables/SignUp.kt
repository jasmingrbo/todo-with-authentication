package ba.grbo.practical.presentation.composables

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import ba.grbo.practical.R
import ba.grbo.practical.framework.mics.DEFAULT

@Composable
fun SignUp(
    onSignUpTextClicked: () -> Unit
) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = LocalContentColor.current)) {
            append(stringResource(R.string.sign_up_text_first_part))
            append(" ")
        }

        pushStringAnnotation(
            tag = "signUp",
            annotation = String.DEFAULT
        )

        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.primary
            )
        ) {
            append(stringResource(R.string.sign_up_text_second_part))
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "signUp",
                start = offset,
                end = offset
            ).firstOrNull()?.let { onSignUpTextClicked() }
        }
    )
}