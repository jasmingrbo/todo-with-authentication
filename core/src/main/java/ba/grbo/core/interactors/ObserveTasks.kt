package ba.grbo.core.interactors

import ba.grbo.core.domain.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface ObserveTasks {
    operator fun invoke(uid: String, scope: CoroutineScope): Flow<List<Task>>
}