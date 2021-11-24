package ba.grbo.core.interactors

import ba.grbo.core.data.Authenticator

class DefaultAuthenticateWithGoogle(
    private val authenticator: Authenticator
) : AuthenticateWithGoogle {
    override suspend fun invoke(idToken: String) = authenticator.googleSignIn(idToken)
}