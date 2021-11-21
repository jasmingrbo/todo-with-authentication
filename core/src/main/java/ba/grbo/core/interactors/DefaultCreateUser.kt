package ba.grbo.core.interactors

import ba.grbo.core.data.Authenticator
import ba.grbo.core.domain.Result.Loading
import ba.grbo.core.domain.Result.Success
import kotlinx.coroutines.flow.flow

class DefaultCreateUser(
    private val authenticator: Authenticator
) : CreateUser {
    override fun invoke(
        email: String,
        password: String
    ) = flow {
        emit(Loading(true))
        emit(Success(authenticator.signUp(email, password)))
    }
}