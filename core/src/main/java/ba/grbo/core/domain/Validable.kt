package ba.grbo.core.domain

sealed interface Validable {
    object Valid : Validable
    class Invalid(val message: Int) : Validable
}