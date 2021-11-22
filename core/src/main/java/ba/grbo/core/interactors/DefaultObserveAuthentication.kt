package ba.grbo.core.interactors

import ba.grbo.core.data.Authenticator

class DefaultObserveAuthentication(
    private val authenticator: Authenticator
) : ObserveAuthentication {
    override fun invoke() = authenticator.observeAuthenticationStatus()
}