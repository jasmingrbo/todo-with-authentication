package ba.grbo.practical.presentation.composables

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.practical.framework.mics.DEFAULT

@Composable
fun Header(
    modifier: Modifier = Modifier,
    @StringRes header: Int,
    @StringRes feedback: Int,
    enabled: Boolean
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            modifier = Modifier.alignByBaseline(),
            text = stringResource(header),
            style = LocalTextStyle.current.copy(
                fontSize = 36.sp,
                color = MaterialTheme.colors.onSurface.copy(
                    if (enabled) MaterialTheme.colors.onSurface.alpha else ContentAlpha.disabled
                )
            )
        )

        Spacer(modifier = Modifier.width(12.dp))

        AnimatedVisibility(
            modifier = Modifier
                .weight(1f)
                .alignByBaseline(),
            visible = feedback != Int.DEFAULT,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = if (feedback != Int.DEFAULT) stringResource(feedback) else String.DEFAULT,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                style = LocalTextStyle.current.copy(fontSize = 14.sp)
            )
        }
    }
}