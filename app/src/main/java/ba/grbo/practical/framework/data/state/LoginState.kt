package ba.grbo.practical.framework.data.state

import ba.grbo.core.domain.Email
import ba.grbo.core.domain.Password

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