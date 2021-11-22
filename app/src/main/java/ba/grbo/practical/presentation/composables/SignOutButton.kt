package ba.grbo.practical.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ba.grbo.practical.R

@Composable
fun SignOutButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit
) {
    CustomButton(
        modifier = modifier,
        text = R.string.sign_out,
        enabled = enabled,
        onClick = onClick
    )
}