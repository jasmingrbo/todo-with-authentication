package ba.grbo.practical.framework.data.state

sealed interface RestorePasswordEvent {
    class EmailChanged(val email: String): RestorePasswordEvent
    object ResetEmailButtonClicked: RestorePasswordEvent
    object RestorePasswordButtonClicked: RestorePasswordEvent
}