package ba.grbo.practical.presentation.screens.home

import TaskInput
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ba.grbo.core.domain.Task
import ba.grbo.practical.R
import ba.grbo.practical.presentation.composables.SignOutButton
import ba.grbo.practical.presentation.composables.Tasks

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    email: String,
    enabled: Boolean,
    tasks: List<Task>,
    task: String,
    onTaskChanged: (String) -> Unit,
    onAddTaskButtonClicked: () -> Unit,
    onDeleteTaskButtonClicked: (String) -> Unit,
    onSignOutButtonClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hi there: $email")

        Spacer(modifier = Modifier.height(16.dp))

        SignOutButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            onClick = onSignOutButtonClicked
        )

        Spacer(modifier = Modifier.height(16.dp))

        Divider()

        Spacer(modifier = Modifier.height(16.dp))

        TaskInput(
            task = task,
            onTaskChange = onTaskChanged,
            onAddTaskButtonClicked = onAddTaskButtonClicked
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = stringResource(R.string.tasks))

        Spacer(modifier = Modifier.height(16.dp))

        Tasks(
            tasks = tasks,
            onDeleteTaskButtonClicked = onDeleteTaskButtonClicked
        )
    }
}