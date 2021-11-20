package ba.grbo.practical.presentation.composables

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.practical.R
import ba.grbo.practical.presentation.keyboardAsState

@Composable
fun ThirdPartyLoginButtons(
    modifier: Modifier = Modifier,
    onGoogleLoginButtonClicked: () -> Unit,
    onFacebookLoginButtonClicked: () -> Unit
) {
    val keyboardOpened by keyboardAsState()
    val spacer by animateDpAsState(if (keyboardOpened) 12.dp else 24.dp)
    Column(
        modifier = modifier,
        verticalArrangement =Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.alternative_login),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(spacer))

        ThirdPartyCredentialReceiverButtons(
            modifier = Modifier.fillMaxWidth(),
            onGoogleButtonClicked = onGoogleLoginButtonClicked,
            onFacebookButtonClicked = onFacebookLoginButtonClicked
        )
    }
}