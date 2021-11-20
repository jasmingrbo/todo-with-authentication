package ba.grbo.practical.framework.data.state

sealed interface LoginEvent {
    class EmailChanged(val email: String): LoginEvent
    class PasswordChanged(val password: String): LoginEvent
    object LoginButtonClicked: LoginEvent
    object GoogleLoginButtonClicked: LoginEvent
    object FacebookLoginButtonClicked: LoginEvent
}