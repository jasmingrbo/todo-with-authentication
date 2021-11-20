package ba.grbo.practical.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.practical.R

@Composable
fun ThirdPartyLoginButtons(
    modifier: Modifier = Modifier,
    onGoogleLoginButtonClicked: () -> Unit,
    onFacebookLoginButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement =Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.alternative_login),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        ThirdPartyCredentialReceiverButtons(
            modifier = Modifier.fillMaxWidth(),
            onGoogleButtonClicked = onGoogleLoginButtonClicked,
            onFacebookButtonClicked = onFacebookLoginButtonClicked
        )
    }
}