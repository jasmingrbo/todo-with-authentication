package ba.grbo.core.data

import ba.grbo.core.domain.User
import kotlinx.coroutines.flow.Flow

interface Authenticator {
    suspend fun login(email: String, password: String): Boolean

    suspend fun signUp(email: String, password: String): Boolean

    fun logout()

    suspend fun isLoggedIn(): Boolean

    fun observeAuthenticationStatus(): Flow<Boolean>

    fun getUser(): User
}