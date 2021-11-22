package ba.grbo.core.interactors

import ba.grbo.core.domain.User

interface FetchUser {
    operator fun invoke(): User
}