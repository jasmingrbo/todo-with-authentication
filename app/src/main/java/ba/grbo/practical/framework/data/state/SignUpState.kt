package ba.grbo.practical.framework.data.state

import ba.grbo.practical.framework.mics.DEFAULT

data class SignUpState(
    val email: String,
    val password: Password,
    val feedback: Feedback
) {
    data class Password(
        val value: String,
        val repeated: String,
        val masked: Boolean,
        val repeatedMasked: Boolean
    ) {
        companion object {
            val DEFAULT = Password(
                value = String.DEFAULT,
                repeated = String.DEFAULT,
                masked = true,
                repeatedMasked = true
            )
        }
    }

    companion object {
        val DEFAULT = SignUpState(
            email = String.DEFAULT,
            password = Password.DEFAULT,
            feedback = Feedback.DEFAULT
        )
    }
}