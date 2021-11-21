package ba.grbo.core.data

interface Authenticator {
    suspend fun login(email: String, password: String): Boolean

    suspend fun signUp(email: String, password: String): Boolean

    suspend fun logout()

    suspend fun isLoggedIn(): Boolean
}