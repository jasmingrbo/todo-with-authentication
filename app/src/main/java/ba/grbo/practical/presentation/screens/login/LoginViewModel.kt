package ba.grbo.practical.presentation.screens.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.grbo.core.domain.Email
import ba.grbo.core.domain.Password
import ba.grbo.core.domain.Result.Loading
import ba.grbo.core.domain.Result.Success
import ba.grbo.core.domain.Validable
import ba.grbo.core.interactors.LoginUser
import ba.grbo.practical.R
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
import ba.grbo.practical.framework.mics.validateEmail
import ba.grbo.practical.framework.mics.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUser,
    private val IODispatcher: CoroutineDispatcher
) : ViewModel() {
    var state by mutableStateOf(LoginState.DEFAULT)
        private set

    private val _loggedIn = MutableStateFlow<Unit?>(null)
    val loggedIn: StateFlow<Unit?>
        get() = _loggedIn

    fun onEvent(event: LoginEvent) {
        when (event) {
            is EmailChanged -> state = state.copy(
                email = state.email.modifyValue(event.email),
                feedback = Int.DEFAULT
            )
            is ResetEmailButtonClicked -> state = state.copy(
                email = Email.DEFAULT,
                feedback = Int.DEFAULT
            )
            is PasswordChanged -> {
                state = state.copy(
                    password = state.password.modifyValue(event.password),
                    feedback = Int.DEFAULT
                )
            }
            is PasswordVisibilityButtonClicked -> {
                state = state.copy(password = state.password.copy(masked = !state.password.masked))
            }
            is ResetPasswordButtonClicked -> {
                state = state.copy(
                    password = Password.DEFAULT.copy(masked = state.password.masked),
                    feedback = Int.DEFAULT
                )
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

        if (emailValidity is Validable.Valid && passwordValidity is Validable.Valid) loginUser()
        else state = state.copy(
            email = state.email.modifyError(emailValidity),
            password = state.password.modifyError(passwordValidity)
        )
    }

    private fun loginUser() {
        state = state.copy(feedback = Int.DEFAULT)

        login(email = state.email.value, password = state.password.value)
            .flowOn(IODispatcher)
            .onEach { result ->
                when (result) {
                    is Loading -> state = state.copy(loading = result.loading)
                    is Success -> _loggedIn.value = Unit
                }
            }
            .catch { throwable ->
                // TODO Log the throwable
                Log.i("jasmin", "$throwable")
                state = state.copy(
                    loading = false,
                    feedback = R.string.login_failed
                )
            }
            .launchIn(viewModelScope)
    }
}