package ba.grbo.practical.framework.data.state

import ba.grbo.core.domain.Email
import ba.grbo.core.domain.Password
import ba.grbo.practical.framework.mics.DEFAULT

data class LoginState(
    val email: Email,
    val password: Password,
    val loading: Boolean,
    val feedback: Int
) {
    companion object {
        val DEFAULT = LoginState(
            email = Email.DEFAULT,
            password = Password.DEFAULT,
            loading = false,
            feedback = Int.DEFAULT
        )
    }
}