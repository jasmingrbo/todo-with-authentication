package ba.grbo.practical.framework.data.state

import ba.grbo.core.domain.Email
import ba.grbo.core.domain.Password

data class SignUpState(
    val email: Email,
    val password: Password,
    val repeatedPassword: Password,
) {
    companion object {
        val DEFAULT = SignUpState(
            email = Email.DEFAULT,
            password = Password.DEFAULT,
            repeatedPassword = Password.DEFAULT
        )
    }
}