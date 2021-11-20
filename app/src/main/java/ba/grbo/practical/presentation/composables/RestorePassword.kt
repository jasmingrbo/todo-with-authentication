package ba.grbo.practical.presentation.composables

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import ba.grbo.practical.R
import ba.grbo.practical.framework.mics.DEFAULT

@Composable
fun RestorePassword(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val tag = "forgotPassword"
    val annotatedText = buildAnnotatedString {
        pushStringAnnotation(
            tag = tag,
            annotation = String.DEFAULT
        )

        withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
            append(stringResource(R.string.forgot_password))
        }
        pop()
    }

    ClickableText(
        modifier = modifier,
        text = annotatedText,
        style = TextStyle.Default.copy(fontSize = 14.sp),
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = tag,
                start = offset,
                end = offset
            ).firstOrNull()?.let { onClick() }
        }
    )
}