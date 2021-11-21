package ba.grbo.practical.presentation.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.practical.R
import ba.grbo.practical.presentation.keyboardAsState

@Composable
fun ThirdPartySignUpButtons(
    modifier: Modifier = Modifier,
    onGoogleSignUpButtonClicked: () -> Unit,
    onFacebookSignUpButtonClicked: () -> Unit
) {
    val keyboardOpened by keyboardAsState()
    val spacer by animateDpAsState(if (keyboardOpened) 12.dp else 24.dp)

    ThirdPartyCredentialReceiverButtons(
        modifier = modifier,
        onGoogleButtonClicked = onGoogleSignUpButtonClicked,
        onFacebookButtonClicked = onFacebookSignUpButtonClicked
    )

    Spacer(modifier = Modifier.height(spacer))

    Text(
        text = stringResource(R.string.sign_up_with_email),
        fontSize = 14.sp
    )
}