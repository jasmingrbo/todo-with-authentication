package ba.grbo.practical.presentation.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.practical.R

@Composable
fun ThirdPartySignUpButtons(
    modifier: Modifier = Modifier,
    onGoogleSignUpButtonClicked: () -> Unit,
    onFacebookSignUpButtonClicked: () -> Unit
) {
    ThirdPartyCredentialReceiverButtons(
        modifier = modifier,
        onGoogleButtonClicked = onGoogleSignUpButtonClicked,
        onFacebookButtonClicked = onFacebookSignUpButtonClicked
    )

    Spacer(modifier = Modifier.height(24.dp))

    Text(
        text = stringResource(R.string.sign_up_with_email),
        fontSize = 14.sp
    )
}