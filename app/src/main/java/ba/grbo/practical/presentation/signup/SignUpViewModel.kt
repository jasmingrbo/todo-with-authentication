package ba.grbo.practical.presentation.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
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
import ba.grbo.practical.framework.mics.DEFAULT
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(SignUpState.DEFAULT)
        private set

    fun onEvent(event: SignUpEvent): Unit = when (event) {
        is EmailChanged -> state = state.copy(email = event.email)
        is ResetEmailButtonClicked -> state = state.copy(email = String.DEFAULT)
        is PasswordChanged -> {
            state = state.copy(password = state.password.copy(value = event.password))
        }
        is PasswordVisibilityButtonClicked -> {
            state = state.copy(password = state.password.copy(masked = !state.password.masked))
        }
        is ResetPasswordButtonClicked -> {
            state = state.copy(password = state.password.copy(value = String.DEFAULT))
        }
        is RepeatedPasswordChanged -> {
            state = state.copy(password = state.password.copy(repeated = event.password))
        }
        is RepeatedPasswordVisibilityButtonClicked -> {
            state = state.copy(
                password = state.password.copy(repeatedMasked = !state.password.repeatedMasked)
            )
        }
        is ResetRepeatedPasswordButtonClicked -> {
            state = state.copy(password = state.password.copy(repeated = String.DEFAULT))
        }
        is GoogleSignUpButtonClicked -> { /*TODO*/
        }
        is FacebookSignUpButtonClicked -> { /*TODO*/
        }
        is SignUpButtonClicked -> { /*TODO*/
        }
    }
}