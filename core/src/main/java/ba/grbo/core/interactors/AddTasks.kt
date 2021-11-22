package ba.grbo.core.interactors

import ba.grbo.core.domain.Task

interface AddTasks {
    suspend operator fun invoke(tasks: List<Task>, uid: String): Boolean
}