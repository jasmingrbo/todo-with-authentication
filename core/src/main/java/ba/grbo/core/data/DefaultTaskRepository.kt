package ba.grbo.core.data

import ba.grbo.core.domain.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DefaultTaskRepository(
    private val localSource: TaskSource,
    private val remoteSource: TaskSource
) : TaskRepository {
    private lateinit var tasks: Flow<List<Task>>

    override suspend fun insertTasks(tasks: List<Task>, uid: String): Boolean {
        return remoteSource.add(tasks, uid)
    }

    override fun observeTasks(uid: String, scope: CoroutineScope): Flow<List<Task>> {
        tasks = remoteSource.observe(uid)
        scope.collectTasks(uid)
        return localSource.observe(uid)
    }

    private fun CoroutineScope.collectTasks(uid: String) {
        tasks
            .onEach { tasks -> localSource.add(tasks, uid) }
            .launchIn(this)
    }
}