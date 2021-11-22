package ba.grbo.core.interactors

import ba.grbo.core.data.TaskRepository
import ba.grbo.core.domain.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class DefaultObserveTasks(
    private val taskRepository: TaskRepository
): ObserveTasks {
    override fun invoke(uid: String, scope: CoroutineScope): Flow<List<Task>> {
        return taskRepository.observeTasks(uid, scope)
    }
}