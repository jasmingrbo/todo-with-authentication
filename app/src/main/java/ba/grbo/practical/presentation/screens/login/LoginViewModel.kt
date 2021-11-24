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
import ba.grbo.core.interactors.AddTasks
import ba.grbo.core.interactors.FetchUser
import ba.grbo.core.interactors.LoginUser
import ba.grbo.practical.R
import ba.grbo.practical.framework.data.GoogleAuthentication
import ba.grbo.practical.framework.data.state.LoginEvent
import ba.grbo.practical.framework.data.state.LoginEvent.EmailChanged
import ba.grbo.practical.framework.data.state.LoginEvent.FacebookLoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.ForgotPasswordTextClicked
import ba.grbo.practical.framework.data.state.LoginEvent.GoogleLoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.LoginButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.PasswordChanged
import ba.grbo.practical.framework.data.state.LoginEvent.PasswordVisibilityButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.ResetEmailButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.ResetPasswordButtonClicked
import ba.grbo.practical.framework.data.state.LoginEvent.SignUpButtonClicked
import ba.grbo.practical.framework.data.state.LoginState
import ba.grbo.practical.framework.mics.DEFAULT
import ba.grbo.practical.framework.mics.SingleSharedFlow
import ba.grbo.practical.framework.mics.validateEmail
import ba.grbo.practical.framework.mics.validatePassword
import com.google.firebase.FirebaseNetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val online: StateFlow<Boolean>,
    private val login: LoginUser,
    private val fetchUser: FetchUser,
    private val addTasks: AddTasks,
    private val IODispatcher: CoroutineDispatcher
) : ViewModel(), GoogleAuthentication {
    var state by mutableStateOf(LoginState.DEFAULT)
        private set

    override val googleAuthAttempt = SingleSharedFlow<Unit>()

    init {
       online.launchIn(viewModelScope)
    }

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
            is GoogleLoginButtonClicked -> onGoogleLoginButtonClicked()
            is FacebookLoginButtonClicked -> {
                state = state.copy(feedback = R.string.not_implemented)
            }
            SignUpButtonClicked -> {
                state = state.copy(feedback = Int.DEFAULT)
            }
            ForgotPasswordTextClicked -> {
                state = state.copy(feedback = Int.DEFAULT)
            }
        }
    }

    private fun onLoginButtonClicked() {
        state = state.copy(feedback = Int.DEFAULT)

        val emailValidity = validateEmail(state.email.value)
        val passwordValidity = validatePassword(state.password.value)

        if (emailValidity is Validable.Valid && passwordValidity is Validable.Valid) loginUser()
        else state = state.copy(
            email = state.email.modifyError(emailValidity),
            password = state.password.modifyError(passwordValidity)
        )
    }

    private fun onGoogleLoginButtonClicked() {
        if (!online.value) state = state.copy(feedback = R.string.auth_failed_network_error)
        else {
            state = state.copy(
                loading = true,
                feedback = Int.DEFAULT
            )
            googleAuthAttempt.tryEmit(Unit)
        }
    }

    override fun onGoogleAuthSucceeded(isNew: Boolean) {
        val user = fetchUser()
        if (isNew) viewModelScope.launch(IODispatcher) {
            addTasks(getRandomFiveDummyTasks(), user.uid)
        }
    }

    override fun onGoogleAuthFailed(exception: Exception) {
        // TODO Log the exception

        state = state.copy(
            loading = false,
            feedback = getFeedback(exception)
        )
    }

    private fun loginUser() {
        if (!online.value) state = state.copy(feedback = R.string.auth_failed_network_error)
        else {
            state = state.copy(feedback = Int.DEFAULT)

            login(email = state.email.value, password = state.password.value)
                .flowOn(IODispatcher)
                .onEach { result ->
                    when (result) {
                        is Loading -> state = state.copy(loading = result.loading)
                        is Success -> {} // Nothing, will be picked up by the observer in PracticalViewModel
                    }
                }
                .catch { throwable ->
                    // TODO Log the throwable
                    // Ignoring the type of throwable (like network exception) for the sake
                    // of simplicity, for all problems giving a generic feedback
                    state = state.copy(
                        loading = false,
                        feedback = when (throwable) {
                            is FirebaseNetworkException,
                            is TimeoutCancellationException -> R.string.auth_failed_network_error
                            else -> R.string.login_failed
                        }
                    )
                }
                .launchIn(viewModelScope)
        }
    }
}