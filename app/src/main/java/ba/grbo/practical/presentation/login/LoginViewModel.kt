package ba.grbo.practical.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
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
import ba.grbo.practical.framework.mics.DEFAULT
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(LoginState.DEFAULT)
        private set

    fun onEvent(event: LoginEvent): Unit = when (event) {
        is EmailChanged -> {
            state = state.copy(email = event.email)
        }
        is ResetEmailButtonClicked -> {
            state = state.copy(email = String.DEFAULT)
        }
        is PasswordChanged -> {
            state = state.copy(password = state.password.copy(value = event.password))
        }
        is PasswordVisibilityButtonClicked -> {
            state = state.copy(password = state.password.copy(masked = !state.password.masked))
        }
        is ResetPasswordButtonClicked -> {
            state = state.copy(password = state.password.copy(value = String.DEFAULT))
        }
        is LoginButtonClicked -> { /*TODO*/
        }
        is GoogleLoginButtonClicked -> { /*TODO*/
        }
        is FacebookLoginButtonClicked -> { /*TODO*/
        }
    }


}