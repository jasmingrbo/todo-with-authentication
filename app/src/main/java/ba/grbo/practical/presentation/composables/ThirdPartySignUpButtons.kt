package ba.grbo.practical.presentation.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.practical.R

@Composable
fun ThirdPartySignUpButtons(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onGoogleSignUpButtonClicked: () -> Unit,
    onFacebookSignUpButtonClicked: () -> Unit
) {
    val keyboardOpened by keyboardAsState()
    val spacer by animateDpAsState(if (keyboardOpened) 12.dp else 24.dp)

    ThirdPartyCredentialReceiverButtons(
        modifier = modifier,
        enabled = enabled,
        onGoogleButtonClicked = onGoogleSignUpButtonClicked,
        onFacebookButtonClicked = onFacebookSignUpButtonClicked
    )

    Spacer(modifier = Modifier.height(spacer))

    Text(
        text = stringResource(R.string.sign_up_with_email),
        style = LocalTextStyle.current.copy(
            fontSize = 14.sp,
            color = MaterialTheme.colors.onSurface.copy(
                if (enabled) MaterialTheme.colors.onSurface.alpha else ContentAlpha.disabled
            )
        )
    )
}