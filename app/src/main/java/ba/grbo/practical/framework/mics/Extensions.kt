package ba.grbo.practical.framework.mics

import android.util.Patterns
import ba.grbo.practical.R
import ba.grbo.practical.framework.data.state.Validable
import ba.grbo.practical.framework.data.state.Validable.Invalid
import ba.grbo.practical.framework.data.state.Validable.Valid

val String.Companion.DEFAULT: String
    get() = ""

val Int.Companion.DEFAULT: Int
    get() = 0

fun validateEmail(email: String): Validable = when {
    email.isEmpty() -> Invalid(R.string.email_empty)
    !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Invalid(R.string.email_invalid)
    else -> Valid
}

fun validatePassword(password: String): Validable = when {
    password.isEmpty() -> Invalid(R.string.password_empty)
    password.length < 3 -> Invalid(R.string.password_invalid)
    else -> Valid
}

fun validatePasswords(password: String, repeatedPassword: String): Validable = when {
    password != repeatedPassword -> Invalid(R.string.passwords_invalid)
    else -> Valid
}
