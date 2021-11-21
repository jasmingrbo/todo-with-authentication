package ba.grbo.practical.presentation.screens.restorepassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ba.grbo.core.domain.Email
import ba.grbo.core.domain.Validable
import ba.grbo.practical.framework.data.state.RestorePasswordEvent
import ba.grbo.practical.framework.data.state.RestorePasswordEvent.EmailChanged
import ba.grbo.practical.framework.data.state.RestorePasswordEvent.ResetEmailButtonClicked
import ba.grbo.practical.framework.data.state.RestorePasswordEvent.RestorePasswordButtonClicked
import ba.grbo.practical.framework.data.state.RestorePasswordState
import ba.grbo.practical.framework.mics.validateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestorePasswordViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(RestorePasswordState.DEFAULT)
        private set

    fun onEvent(event: RestorePasswordEvent) {
        when (event) {
            is EmailChanged -> state = state.copy(email = state.email.modifyValue(event.email))
            is ResetEmailButtonClicked -> state = state.copy(email = Email.DEFAULT)
            is RestorePasswordButtonClicked -> {
                val emailValidity = validateEmail(state.email.value)
                if (emailValidity is Validable.Valid) {
                    // TODO Make a request to restore the password
                } else state = state.copy(email = state.email.modifyError(emailValidity))
            }
        }
    }
}