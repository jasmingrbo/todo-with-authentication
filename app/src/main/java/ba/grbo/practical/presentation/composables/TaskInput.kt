import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ba.grbo.practical.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TaskInput(
    modifier: Modifier = Modifier,
    task: String,
    onTaskChange: (String) -> Unit,
    onAddTaskButtonClicked: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.05f),
        shape = RectangleShape
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                modifier = Modifier
                    .padding(12.dp)
                    .weight(1f),
                value = task,
                onValueChange = onTaskChange,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    onAddTaskButtonClicked()
                    keyboardController?.hide()
                }),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent)
            )

            Spacer(modifier = Modifier.width(12.dp))

            TextButton(
                modifier = Modifier.padding(horizontal = 6.dp),
                enabled = task.isNotEmpty(),
                onClick = {
                    onAddTaskButtonClicked()
                    keyboardController?.hide()
                }
            ) {
                Text(text = stringResource(R.string.add_button))
            }
        }
    }
}