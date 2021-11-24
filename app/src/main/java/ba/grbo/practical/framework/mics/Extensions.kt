package ba.grbo.practical.framework.mics

import android.util.Patterns
import ba.grbo.core.domain.Validable
import ba.grbo.core.domain.Validable.Invalid
import ba.grbo.core.domain.Validable.Valid
import ba.grbo.practical.R
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

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
    password.length < 6 -> Invalid(R.string.password_invalid)
    else -> Valid
}

fun validatePasswords(password: String, repeatedPassword: String): Validable = when {
    password != repeatedPassword -> Invalid(R.string.passwords_invalid)
    else -> Valid
}

// Note: Make sure to collect from flow before any value is emitted, otherwise all values emitted
// before collecting the flow are lost (not acknowledged).
@Suppress("FunctionName", "UNCHECKED_CAST")
fun <T> SingleSharedFlow() = MutableSharedFlow<T>(
    onBufferOverflow = BufferOverflow.DROP_OLDEST,
    extraBufferCapacity = 1
)
