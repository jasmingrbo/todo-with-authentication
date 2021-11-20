package ba.grbo.practical.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ba.grbo.practical.R

@Composable
fun FacebookButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ThirdPartyCredentialsReceiverButton(
        modifier = modifier,
        image = R.drawable.facebook,
        imageDescription = R.string.facebook_image_description,
        onClick = onClick
    )
}