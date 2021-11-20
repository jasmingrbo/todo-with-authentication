package ba.grbo.practical.framework.data.state

import ba.grbo.practical.framework.mics.DEFAULT

data class LoginState(
    val email: String,
    val password: Password,
    val feedback: Feedback
) {
    data class Password(
        val value: String,
        val masked: Boolean
    ) {
        companion object {
            val DEFAULT = Password(
                value = String.DEFAULT,
                masked = true
            )
        }
    }

    companion object {
        val DEFAULT = LoginState(
            email = String.DEFAULT,
            password = Password.DEFAULT,
            feedback = Feedback.DEFAULT
        )
    }
}