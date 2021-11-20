package ba.grbo.practical.framework.data.state

import ba.grbo.practical.framework.mics.DEFAULT

data class RestorePasswordState(
    val email: String,
    val feedback: Feedback
) {
    companion object {
        val DEFAULT = RestorePasswordState(
            email = String.DEFAULT,
            feedback = Feedback.DEFAULT
        )
    }
}