package ba.grbo.practical.presentation.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = CircleShape,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = stringResource(text)
        )
    }

}