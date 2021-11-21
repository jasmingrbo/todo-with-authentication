package ba.grbo.practical.presentation.screens.signup

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
import ba.grbo.core.domain.Validable.Valid
import ba.grbo.core.interactors.CreateUser
import ba.grbo.practical.R
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
import ba.grbo.practical.framework.mics.validateEmail
import ba.grbo.practical.framework.mics.validatePassword
import ba.grbo.practical.framework.mics.validatePasswords
import com.google.firebase.auth.FirebaseAuthUserCollisionException
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
class SignUpViewModel @Inject constructor(
    private val signUp: CreateUser,
    private val IODispatcher: CoroutineDispatcher
) : ViewModel() {
    var state by mutableStateOf(SignUpState.DEFAULT)
        private set

    private val _signedUp = MutableStateFlow<Unit?>(null)
    val signedUp: StateFlow<Unit?>
        get() = _signedUp

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
        state = state.copy(feedback = Int.DEFAULT)

        signUp(email = state.email.value, password = state.password.value)
            .flowOn(IODispatcher)
            .onEach { result ->
                when (result) {
                    is Loading -> state = state.copy(loading = result.loading)
                    is Success -> _signedUp.value = Unit
                }
            }
            .catch { throwable ->
                // TODO Log the throwable
                Log.i("jasmin", "$throwable")
                state = state.copy(
                    loading = false,
                    feedback = if (throwable is FirebaseAuthUserCollisionException)
                        R.string.sign_up_failed_email_used else R.string.sign_up_failed_generic
                )
            }
            .launchIn(viewModelScope)
    }
}