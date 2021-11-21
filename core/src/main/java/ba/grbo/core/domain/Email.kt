package ba.grbo.core.domain

data class Email(
    override val value: String,
    override val isError: Boolean,
    override val errorMessage: Int
) : Inputable {
    fun modifyValue(value: String) = DEFAULT.copy(value = value)

    fun modifyError(emailValidity: Validable) = Email(
        value = value,
        isError = emailValidity is Validable.Invalid,
        errorMessage = if (emailValidity is Validable.Invalid) emailValidity.message else Int.DEFAULT
    )

    companion object {
        val DEFAULT = Email(
            value = String.DEFAULT,
            isError = false,
            errorMessage = Int.DEFAULT
        )
    }
}


