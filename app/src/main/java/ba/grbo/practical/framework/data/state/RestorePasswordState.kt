package ba.grbo.practical.framework.data.state

data class RestorePasswordState(val email: Email) {
    companion object {
        val DEFAULT = RestorePasswordState(email = Email.DEFAULT)
    }
}