package ba.grbo.practical.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ba.grbo.practical.R

@Composable
fun FacebookButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit
) {
    ThirdPartyCredentialsReceiverButton(
        modifier = modifier,
        image = R.drawable.facebook,
        imageDescription = R.string.facebook_image_description,
        enabled = enabled,
        onClick = onClick
    )
}