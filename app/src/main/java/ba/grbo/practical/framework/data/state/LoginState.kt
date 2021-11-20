package ba.grbo.practical.framework.data.state

import ba.grbo.practical.framework.mics.DEFAULT

data class LoginState(
    val email: String,
    val password: String,
    val feedback: String
) {
    companion object {
        val DEFAULT = LoginState(
            email = String.DEFAULT,
            password = String.DEFAULT,
            feedback = String.DEFAULT
        )
    }
}