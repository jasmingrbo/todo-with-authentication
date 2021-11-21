package ba.grbo.practical.presentation.screens.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ba.grbo.core.domain.Email
import ba.grbo.core.domain.Password
import ba.grbo.core.domain.Validable
import ba.grbo.practical.framework.data.state.LoginEvent
import ba.grbo.practical.framework.data.state.LoginEvent.EmailChanged
import ba.grbo.practical.framework.data.state.LoginEvent.FacebookLoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.GoogleLoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.LoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.PasswordChanged
import ba.grbo.practical.framework.data.state.LoginEvent.PasswordVisibilityButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.ResetEmailButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.ResetPasswordButtonClicked
import ba.grbo.practical.framework.data.state.LoginState
import ba.grbo.practical.framework.mics.validateEmail
import ba.grbo.practical.framework.mics.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(LoginState.DEFAULT)
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
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
            is LoginButtonClicked -> onLoginButtonClicked()
            is GoogleLoginButtonClicked -> { /*TODO*/
            }
            is FacebookLoginButtonClicked -> { /*TODO*/
            }
        }
    }

    private fun onLoginButtonClicked() {
        val emailValidity = validateEmail(state.email.value)
        val passwordValidity = validatePassword(state.password.value)

        if (emailValidity is Validable.Valid && passwordValidity is Validable.Valid) {
            // TODO Make a request to login
        } else {
            state = state.copy(
                email = state.email.modifyError(emailValidity),
                password = state.password.modifyError(passwordValidity)
            )
        }
    }
}