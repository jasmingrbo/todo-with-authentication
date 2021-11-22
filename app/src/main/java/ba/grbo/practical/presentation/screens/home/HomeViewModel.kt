package ba.grbo.practical.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.grbo.core.domain.Task
import ba.grbo.core.interactors.AddTasks
import ba.grbo.core.interactors.FetchUser
import ba.grbo.core.interactors.ObserveTasks
import ba.grbo.core.interactors.SignOutUser
import ba.grbo.practical.framework.data.state.HomeEvent
import ba.grbo.practical.framework.data.state.HomeEvent.AddTaskButtonClicked
import ba.grbo.practical.framework.data.state.HomeEvent.DeleteTaskButtonClicked
import ba.grbo.practical.framework.data.state.HomeEvent.SignOutButtonClicked
import ba.grbo.practical.framework.data.state.HomeEvent.TaskChanged
import ba.grbo.practical.framework.data.state.HomeState
import ba.grbo.practical.framework.mics.DEFAULT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    fetchUser: FetchUser,
    observeTasks: ObserveTasks,
    private val addTasks: AddTasks,
    private val signOut: SignOutUser,
    private val IODispatcher: CoroutineDispatcher
) : ViewModel() {
    private val user = fetchUser()
    var state by mutableStateOf(
        HomeState(
            email = user.email,
            enabled = true,
            tasks = listOf(),
            task = String.DEFAULT
        )
    )
        private set

    init {
        observeTasks(user.uid, viewModelScope)
            .distinctUntilChanged()
            .onEach { tasks -> state = state.copy(tasks = tasks) }
            .catch { exception ->
                // TODO Log the exception
                // TODO Inform the user
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is TaskChanged -> state = state.copy(task = event.task)
            is SignOutButtonClicked -> signOut()
            is AddTaskButtonClicked -> viewModelScope.updateTasks()
            is DeleteTaskButtonClicked -> viewModelScope.deleteTask(event.task)
        }
    }

    private fun CoroutineScope.updateTasks() = launch(IODispatcher) {
        addTasks(
            tasks = listOf(Task(state.task), *state.tasks.toTypedArray()),
            uid = user.uid
        )
        state = state.copy(task = String.DEFAULT)
    }

    private fun CoroutineScope.deleteTask(task: String) = launch(IODispatcher) {
        // For simplicity re-adding tasks, without the one that should be deleted
        addTasks(
            tasks = state.tasks.toMutableList().apply { remove(Task(task)) },
            uid = user.uid
        )
    }
}