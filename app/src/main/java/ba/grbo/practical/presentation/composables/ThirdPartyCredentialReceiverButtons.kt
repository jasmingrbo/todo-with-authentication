package ba.grbo.practical.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThirdPartyCredentialReceiverButtons(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onGoogleButtonClicked: () -> Unit,
    onFacebookButtonClicked: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        GoogleButton(
            modifier = Modifier.size(60.dp),
            enabled = enabled,
            onClick = onGoogleButtonClicked
        )

        Spacer(modifier = Modifier.width(16.dp))

        FacebookButton(
            modifier = Modifier.size(60.dp),
            enabled = enabled,
            onClick = onFacebookButtonClicked
        )
    }
}