package ba.grbo.core.domain

sealed interface Result<out R> {
    data class Success<out T>(val data: T) : Result<T>
    data class Loading(val loading: Boolean) : Result<Nothing>
}