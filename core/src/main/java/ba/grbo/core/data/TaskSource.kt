package ba.grbo.core.data

import ba.grbo.core.domain.Task
import kotlinx.coroutines.flow.Flow

interface TaskSource {
    suspend fun add(tasks: List<Task>, uid: String): Boolean



    fun observe(uid: String): Flow<List<Task>>
}