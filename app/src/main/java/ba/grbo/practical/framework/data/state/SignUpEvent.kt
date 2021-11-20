package ba.grbo.practical.framework.data.state

sealed interface SignUpEvent {
    class EmailChanged(val email: String): SignUpEvent
    object ResetEmailButtonClicked: SignUpEvent
    class PasswordChanged(val password: String): SignUpEvent
    object PasswordVisibilityButtonClicked: SignUpEvent
    object ResetPasswordButtonClicked: SignUpEvent
    class RepeatedPasswordChanged(val password: String): SignUpEvent
    object RepeatedPasswordVisibilityButtonClicked: SignUpEvent
    object ResetRepeatedPasswordButtonClicked: SignUpEvent
    object GoogleSignUpButtonClicked: SignUpEvent
    object FacebookSignUpButtonClicked: SignUpEvent
    object SignUpButtonClicked: SignUpEvent
}