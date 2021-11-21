package ba.grbo.practical.presentation.composables

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import ba.grbo.practical.R
import ba.grbo.practical.framework.mics.DEFAULT

@Composable
fun SignUp(
    enabled: Boolean,
    onSignUpTextClicked: () -> Unit
) {
    val tag = "signUp"
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = LocalContentColor.current.copy(
                    if (enabled) LocalContentColor.current.alpha else ContentAlpha.disabled
                )
            )
        ) {
            append(stringResource(R.string.sign_up_text_first_part))
            append(" ")
        }

        pushStringAnnotation(
            tag = tag,
            annotation = String.DEFAULT
        )

        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.primary.copy(
                    alpha = if (enabled) MaterialTheme.colors.primary.alpha else ContentAlpha.disabled
                )
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
                tag = tag,
                start = offset,
                end = offset
            ).firstOrNull()?.let { if (enabled) onSignUpTextClicked() }
        }
    )
}