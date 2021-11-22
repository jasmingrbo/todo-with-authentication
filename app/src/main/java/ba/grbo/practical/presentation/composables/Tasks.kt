package ba.grbo.practical.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ba.grbo.core.domain.Task
import ba.grbo.practical.R

@Composable
fun Tasks(
    modifier: Modifier = Modifier,
    tasks: List<Task>,
    onDeleteTaskButtonClicked: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(tasks) { task ->
            TaskItem(
                modifier = Modifier.fillMaxWidth(),
                task = task,
                onDeleteTaskButtonClicked = onDeleteTaskButtonClicked
            )
        }
    }
}

@Composable
fun TaskItem(
    modifier: Modifier = Modifier,
    task: Task,
    onDeleteTaskButtonClicked: (String) -> Unit
) {
    Card(
        modifier = modifier.padding(6.dp),
        elevation = 4.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 18.dp)
                    .weight(1f),
                text = task.value
            )

            IconButton(
                modifier = Modifier.padding(6.dp),
                onClick = { onDeleteTaskButtonClicked(task.value) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete_icon_description)
                )
            }

        }
    }
}