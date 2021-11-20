package ba.grbo.practical.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ba.grbo.practical.R

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ThirdPartyCredentialsReceiverButton(
        modifier = modifier,
        image = R.drawable.google,
        imageDescription = R.string.google_image_description,
        onClick = onClick
    )
}