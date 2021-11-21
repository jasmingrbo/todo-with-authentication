package ba.grbo.practical.framework.data.state

import androidx.annotation.StringRes
import ba.grbo.core.domain.DEFAULT
import ba.grbo.core.domain.Email
import ba.grbo.core.domain.Password

data class SignUpState(
    val email: Email,
    val password: Password,
    val repeatedPassword: Password,
    val loading: Boolean,
    @StringRes val feedback: Int
) {
    companion object {
        val DEFAULT = SignUpState(
            email = Email.DEFAULT,
            password = Password.DEFAULT,
            repeatedPassword = Password.DEFAULT,
            loading = false,
            feedback = Int.DEFAULT
        )
    }
}