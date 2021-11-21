package ba.grbo.practical.presentation.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ba.grbo.core.domain.Email
import ba.grbo.core.domain.Password
import ba.grbo.core.domain.Validable
import ba.grbo.practical.framework.data.state.SignUpEvent
import ba.grbo.practical.framework.data.state.SignUpEvent.EmailChanged
import ba.grbo.practical.framework.data.state.SignUpEvent.FacebookSignUpButtonClicked
import ba.grbo.practical.framework.data.state.SignUpEvent.GoogleSignUpButtonClicked
import ba.grbo.practical.framework.data.state.SignUpEvent.PasswordChanged
import ba.grbo.practical.framework.data.state.SignUpEvent.PasswordVisibilityButtonClicked
import ba.grbo.practical.framework.data.state.SignUpEvent.RepeatedPasswordChanged
import ba.grbo.practical.framework.data.state.SignUpEvent.RepeatedPasswordVisibilityButtonClicked
import ba.grbo.practical.framework.data.state.SignUpEvent.ResetEmailButtonClicked
import ba.grbo.practical.framework.data.state.SignUpEvent.ResetPasswordButtonClicked
import ba.grbo.practical.framework.data.state.SignUpEvent.ResetRepeatedPasswordButtonClicked
import ba.grbo.practical.framework.data.state.SignUpEvent.SignUpButtonClicked
import ba.grbo.practical.framework.data.state.SignUpState
import ba.grbo.practical.framework.mics.validateEmail
import ba.grbo.practical.framework.mics.validatePassword
import ba.grbo.practical.framework.mics.validatePasswords
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(SignUpState.DEFAULT)
        private set

    fun onEvent(event: SignUpEvent): Unit = when (event) {
        is EmailChanged -> state = state.copy(email = state.email.modifyValue(event.email))
        is ResetEmailButtonClicked -> state = state.copy(email = Email.DEFAULT)
        is PasswordChanged -> {
            state = state.copy(password = state.password.modifyValue(event.password))
        }
        is PasswordVisibilityButtonClicked -> {
            state = state.copy(password = state.password.copy(masked = !state.password.masked))
        }
        is ResetPasswordButtonClicked -> {
            state = state.copy(password = Password.DEFAULT.copy(masked = state.password.masked))
        }
        is RepeatedPasswordChanged -> {
            state = state.copy(
                repeatedPassword = state.repeatedPassword.modifyValue(event.password)
            )
        }
        is RepeatedPasswordVisibilityButtonClicked -> {
            state = state.copy(
                repeatedPassword = state.repeatedPassword.copy(
                    masked = !state.repeatedPassword.masked
                )
            )
        }
        is ResetRepeatedPasswordButtonClicked -> {
            state = state.copy(
                repeatedPassword = Password.DEFAULT.copy(masked = state.repeatedPassword.masked)
            )
        }
        is GoogleSignUpButtonClicked -> { /*TODO*/
        }
        is FacebookSignUpButtonClicked -> { /*TODO*/
        }
        is SignUpButtonClicked -> onSignUpButtonClicked()
    }

    private fun onSignUpButtonClicked() {
        val emailValidity = validateEmail(state.email.value)
        val passwordValidity = validatePassword(state.password.value)
        val passwordsValidity = validatePasswords(
            password = state.password.value,
            repeatedPassword = state.repeatedPassword.value
        )

        if (emailValidity is Validable.Valid && passwordValidity is Validable.Valid && passwordsValidity is Validable.Valid) {
            // TODO Make a request to sign up
        } else {
            state = state.copy(
                email = state.email.modifyError(emailValidity),
                password = state.password.modifyError(passwordValidity),
                repeatedPassword = state.repeatedPassword.modifyError(passwordsValidity)
            )
        }
    }
}