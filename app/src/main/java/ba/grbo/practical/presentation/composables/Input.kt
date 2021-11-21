package ba.grbo.practical.presentation.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ba.grbo.practical.framework.mics.DEFAULT

@Composable
fun Input(
    modifier: Modifier = Modifier,
    value: String,
    enabled: Boolean,
    isError: Boolean,
    @StringRes errorMessage: Int,
    @StringRes label: Int,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    onValueChange: (String) -> Unit,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    Column(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            label = { Text(text = stringResource(id = label)) },
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(2.dp))

        if (errorMessage != Int.DEFAULT) Text(
            modifier = Modifier.align(Alignment.Start),
            text = stringResource(id = errorMessage),
            color = MaterialTheme.colors.error,
            style = LocalTextStyle.current.copy(fontSize = 12.sp)
        )
    }
}