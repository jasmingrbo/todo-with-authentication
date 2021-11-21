package ba.grbo.practical.framework.data.state

import ba.grbo.core.domain.Email

data class RestorePasswordState(val email: Email) {
    companion object {
        val DEFAULT = RestorePasswordState(email = Email.DEFAULT)
    }
}