package ba.grbo.practical.framework.data.state

data class LoginState(
    val email: Email,
    val password: Password,
) {
    companion object {
        val DEFAULT = LoginState(
            email = Email.DEFAULT,
            password = Password.DEFAULT,
        )
    }
}