package ba.grbo.practical.presentation.screens.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.grbo.core.domain.Email
import ba.grbo.core.domain.Password
import ba.grbo.core.domain.Result.Loading
import ba.grbo.core.domain.Result.Success
import ba.grbo.core.domain.Validable.Valid
import ba.grbo.core.interactors.AddTasks
import ba.grbo.core.interactors.CreateUser
import ba.grbo.core.interactors.FetchUser
import ba.grbo.practical.R
import ba.grbo.practical.framework.data.GoogleAuthentication
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
import ba.grbo.practical.framework.mics.SingleSharedFlow
import ba.grbo.practical.framework.mics.validateEmail
import ba.grbo.practical.framework.mics.validatePassword
import ba.grbo.practical.framework.mics.validatePasswords
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
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
class SignUpViewModel @Inject constructor(
    private val online: StateFlow<Boolean>,
    private val signUp: CreateUser,
    private val fetchUser: FetchUser,
    private val addTasks: AddTasks,
    private val IODispatcher: CoroutineDispatcher
) : ViewModel(), GoogleAuthentication {
    var state by mutableStateOf(SignUpState.DEFAULT)
        private set

    override val googleAuthAttempt = SingleSharedFlow<Unit>()

    init {
        online.launchIn(viewModelScope)
    }

    fun onEvent(event: SignUpEvent): Unit = when (event) {
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
        is RepeatedPasswordChanged -> {
            state = state.copy(
                repeatedPassword = state.repeatedPassword.modifyValue(event.password),
                feedback = Int.DEFAULT
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
                repeatedPassword = Password.DEFAULT.copy(masked = state.repeatedPassword.masked),
                feedback = Int.DEFAULT
            )
        }
        is GoogleSignUpButtonClicked -> onGoogleSignUpButtonClicked()
        is FacebookSignUpButtonClicked -> {
            state = state.copy(feedback = R.string.not_implemented)
        }
        is SignUpButtonClicked -> onSignUpButtonClicked()
    }

    private fun onSignUpButtonClicked() {
        state = state.copy(feedback = Int.DEFAULT)

        val emailValidity = validateEmail(state.email.value)
        val passwordValidity = validatePassword(state.password.value)
        val passwordsValidity = validatePasswords(
            password = state.password.value,
            repeatedPassword = state.repeatedPassword.value
        )

        if (emailValidity is Valid && passwordValidity is Valid && passwordsValidity is Valid) {
            signUpUser()
        } else {
            state = state.copy(
                email = state.email.modifyError(emailValidity),
                password = state.password.modifyError(passwordValidity),
                repeatedPassword = state.repeatedPassword.modifyError(passwordsValidity)
            )
        }
    }

    private fun signUpUser() {
        if (!online.value) state = state.copy(feedback = R.string.auth_failed_network_error)
        else {
            state = state.copy(feedback = Int.DEFAULT)

            signUp(email = state.email.value, password = state.password.value)
                .flowOn(IODispatcher)
                .onEach { result ->
                    when (result) {
                        is Loading -> state = state.copy(loading = result.loading)
                        is Success -> {
                            // Prepopulate new users with 5 random tasks, to show the list right away
                            val user = fetchUser()
                            addTasks(getRandomFiveDummyTasks(), user.uid)
                        }
                    }
                }
                .catch { throwable ->
                    // TODO Log the throwable
                    state = state.copy(
                        loading = false,
                        feedback = when (throwable) {
                            is FirebaseNetworkException,
                            is TimeoutCancellationException -> R.string.auth_failed_network_error
                            is FirebaseAuthUserCollisionException -> R.string.sign_up_failed_email_used
                            else -> R.string.sign_up_failed_generic
                        }
                    )
                }
                .launchIn(viewModelScope)
        }
    }

    private fun onGoogleSignUpButtonClicked() {
        if (!online.value) state = state.copy(
            email = Email.DEFAULT,
            password = Password.DEFAULT,
            feedback = R.string.auth_failed_network_error
        )
        else {
            state = state.copy(
                email = Email.DEFAULT,
                password = Password.DEFAULT,
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
}