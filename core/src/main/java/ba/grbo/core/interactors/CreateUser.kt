package ba.grbo.core.interactors

import ba.grbo.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface CreateUser {
    operator fun invoke(email: String, password: String): Flow<Result<Boolean>>
}