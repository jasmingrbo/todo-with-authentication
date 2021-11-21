package ba.grbo.core.interactors

import ba.grbo.core.data.Authenticator

class DefaultIsAuthenticated(
    private val authenticator: Authenticator
): IsAuthenticated {
    override suspend fun invoke() = authenticator.isLoggedIn()
}