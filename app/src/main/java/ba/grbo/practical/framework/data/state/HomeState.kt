package ba.grbo.practical.framework.data.state

import ba.grbo.core.domain.Task

data class HomeState(
    val email: String,
    val enabled: Boolean,
    val tasks: List<Task>,
    val task: String
)
