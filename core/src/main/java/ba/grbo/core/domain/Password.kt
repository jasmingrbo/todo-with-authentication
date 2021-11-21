package ba.grbo.core.domain

data class Password(
    override val value: String,
    val masked: Boolean,
    override val isError: Boolean,
    override val errorMessage: Int,
) : Inputable {
    fun modifyValue(value: String) = DEFAULT.copy(value = value, masked = masked)

    fun modifyError(passwordValidity: Validable) = Password(
        value = value,
        masked = masked,
        isError = passwordValidity is Validable.Invalid,
        errorMessage = if (passwordValidity is Validable.Invalid) passwordValidity.message else Int.DEFAULT
    )

    companion object {
        val DEFAULT = Password(
            value = String.DEFAULT,
            masked = true,
            isError = false,
            errorMessage = Int.DEFAULT
        )
    }
}