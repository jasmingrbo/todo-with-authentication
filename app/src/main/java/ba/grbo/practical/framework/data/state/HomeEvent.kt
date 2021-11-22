package ba.grbo.practical.framework.data.state

sealed interface HomeEvent {
    class TaskChanged(val task: String): HomeEvent
    object SignOutButtonClicked: HomeEvent
    object AddTaskButtonClicked: HomeEvent
    class DeleteTaskButtonClicked(val task: String): HomeEvent
}