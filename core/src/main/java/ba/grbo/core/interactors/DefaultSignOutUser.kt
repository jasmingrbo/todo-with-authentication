package ba.grbo.core.interactors

import ba.grbo.core.data.Authenticator

class DefaultSignOutUser(
    private val authenticator: Authenticator
): SignOutUser {
    override fun invoke() = authenticator.logout()
}