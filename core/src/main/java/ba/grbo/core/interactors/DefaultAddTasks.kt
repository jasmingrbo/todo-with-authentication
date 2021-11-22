package ba.grbo.core.interactors

import ba.grbo.core.data.TaskRepository
import ba.grbo.core.domain.Task

class DefaultAddTasks(
    private val taskRepository: TaskRepository
) : AddTasks {
    override suspend fun invoke(tasks: List<Task>, uid: String): Boolean {
        return taskRepository.insertTasks(tasks, uid)
    }
}