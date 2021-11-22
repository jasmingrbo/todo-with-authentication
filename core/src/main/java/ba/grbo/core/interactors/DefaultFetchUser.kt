package ba.grbo.core.interactors

import ba.grbo.core.data.Authenticator

class DefaultFetchUser(
    private val authenticator: Authenticator
) : FetchUser {
    override fun invoke() = authenticator.getUser()
}