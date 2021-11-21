package ba.grbo.core.interactors

interface IsAuthenticated {
    suspend operator fun invoke(): Boolean
}