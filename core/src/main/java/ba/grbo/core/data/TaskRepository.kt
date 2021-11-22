package ba.grbo.core.data

import ba.grbo.core.domain.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun insertTasks(tasks: List<Task>, uid: String): Boolean

    fun observeTasks(uid: String, scope: CoroutineScope): Flow<List<Task>>
}