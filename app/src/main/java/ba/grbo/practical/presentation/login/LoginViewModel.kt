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
import ba.grbo.practical.framework.data.state.LoginEvent.SignUpTextClicked
import ba.grbo.practical.framework.data.state.LoginState
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
        is PasswordChanged -> {
            state = state.copy(password = event.password)
        }
        is PasswordVisibilityButtonClicked -> {
            state = state.copy(passwordMasked = !state.passwordMasked)
        }
        is LoginButtonClicked -> { /*TODO*/
        }
        is GoogleLoginButtonClicked -> { /*TODO*/
        }
        is FacebookLoginButtonClicked -> { /*TODO*/
        }
        is SignUpTextClicked -> { /*TODO*/
        }
    }


}