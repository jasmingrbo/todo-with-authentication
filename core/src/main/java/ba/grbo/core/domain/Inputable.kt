package ba.grbo.core.domain

interface Inputable {
    val value: String
    val isError: Boolean
    val errorMessage: Int
}