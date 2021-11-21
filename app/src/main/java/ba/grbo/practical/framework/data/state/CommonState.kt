package ba.grbo.practical.framework.data.state

import androidx.annotation.StringRes
import ba.grbo.practical.framework.data.state.Validable.Invalid
import ba.grbo.practical.framework.mics.DEFAULT

data class Email(
    override val value: String,
    override val isError: Boolean,
    @StringRes override val errorMessage: Int
) : Inputable {
    fun modifyValue(value: String) = DEFAULT.copy(value = value)

    fun modifyError(emailValidity: Validable) = Email(
        value = value,
        isError = emailValidity is Invalid,
        errorMessage = if (emailValidity is Invalid) emailValidity.message else Int.DEFAULT
    )

    companion object {
        val DEFAULT = Email(
            value = String.DEFAULT,
            isError = false,
            errorMessage = Int.DEFAULT
        )
    }
}

data class Password(
    override val value: String,
    val masked: Boolean,
    override val isError: Boolean,
    @StringRes override val errorMessage: Int,
) : Inputable {
    fun modifyValue(value: String) = DEFAULT.copy(value = value, masked = masked)

    fun modifyError(passwordValidity: Validable) = Password(
        value = value,
        masked = masked,
        isError = passwordValidity is Invalid,
        errorMessage = if (passwordValidity is Invalid) passwordValidity.message else Int.DEFAULT
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

interface Inputable {
    val value: String
    val isError: Boolean
    val errorMessage: Int
}

sealed interface Validable {
    object Valid : Validable
    class Invalid(@StringRes val message: Int) : Validable
}