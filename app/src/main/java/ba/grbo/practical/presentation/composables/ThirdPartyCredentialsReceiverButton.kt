package ba.grbo.practical.presentation.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun ThirdPartyCredentialsReceiverButton(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int,
    @StringRes imageDescription: Int,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        shape = CircleShape,
        onClick = onClick
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = stringResource(imageDescription)
        )
    }
}