package ba.grbo.practical.framework.data.source.local

import androidx.room.Transaction
import ba.grbo.core.data.TaskSource
import ba.grbo.core.domain.Task
import ba.grbo.practical.framework.data.dto.CacheTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomTaskSource @Inject constructor(
    private val source: TaskDao
) : TaskSource {
    @Transaction
    override suspend fun add(tasks: List<Task>, uid: String): Boolean {
        source.clear()
        source.insert(tasks.map { task -> CacheTask(value = task.value, uid = uid) })
        return true // For simplicity not checking whether the tasks have been inserted into the db
    }

    override fun observe(uid: String): Flow<List<Task>> {
        return source.observe(uid).map { tasks -> tasks.map { task -> Task(value = task.value) } }
    }
}