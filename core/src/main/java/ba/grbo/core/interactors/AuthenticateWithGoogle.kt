package ba.grbo.core.interactors

interface AuthenticateWithGoogle {
    suspend operator fun invoke(idToken: String): Pair<Boolean, Boolean>
}