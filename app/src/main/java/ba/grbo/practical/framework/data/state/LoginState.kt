package ba.grbo.practical.framework.data.state

import ba.grbo.practical.framework.mics.DEFAULT

data class LoginState(
    val email: String,
    val password: String,
    val passwordMasked: Boolean,
    val feedback: Feedback
) {
    data class Feedback(val message: String, val error: Boolean) {
        companion object {
            val DEFAULT = Feedback(message = String.DEFAULT, error = false)
        }
    }

    companion object {
        val DEFAULT = LoginState(
            email = String.DEFAULT,
            password = String.DEFAULT,
            passwordMasked = true,
            feedback = Feedback.DEFAULT
        )
    }
}