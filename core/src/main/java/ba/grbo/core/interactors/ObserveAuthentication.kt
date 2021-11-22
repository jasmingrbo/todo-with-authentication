package ba.grbo.core.interactors

import kotlinx.coroutines.flow.Flow

interface ObserveAuthentication {
    operator fun invoke(): Flow<Boolean>
}