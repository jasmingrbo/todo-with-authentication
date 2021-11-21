package ba.grbo.practical.presentation.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun ThirdPartyCredentialsReceiverButton(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    @StringRes imageDescription: Int,
    enabled: Boolean,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        enabled = enabled,
        shape = CircleShape,
        onClick = onClick
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(imageDescription),
            colorFilter = if (enabled) null else ColorFilter.tint(
                color = MaterialTheme.colors.onSurface.copy(
                    alpha = if (enabled) TextFieldDefaults.IconOpacity else ContentAlpha.disabled
                ),
                blendMode = BlendMode.DstIn
            )
        )
    }
}