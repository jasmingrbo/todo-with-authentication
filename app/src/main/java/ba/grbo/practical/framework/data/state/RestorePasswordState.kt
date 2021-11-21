package ba.grbo.practical.framework.data.state

import androidx.annotation.StringRes
import ba.grbo.core.domain.DEFAULT
import ba.grbo.core.domain.Email

data class RestorePasswordState(
    val email: Email,
    val loading: Boolean,
    @StringRes val feedback: Int,
) {
    companion object {
        val DEFAULT = RestorePasswordState(
            email = Email.DEFAULT,
            loading = false,
            feedback = Int.DEFAULT
        )
    }
}