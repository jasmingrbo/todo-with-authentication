package ba.grbo.practical.presentation.restorepassword

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ba.grbo.practical.framework.data.state.RestorePasswordEvent
import ba.grbo.practical.framework.data.state.RestorePasswordEvent.EmailChanged
import ba.grbo.practical.framework.data.state.RestorePasswordEvent.ResetEmailButtonClicked
import ba.grbo.practical.framework.data.state.RestorePasswordEvent.RestorePasswordButtonClicked
import ba.grbo.practical.framework.data.state.RestorePasswordState
import ba.grbo.practical.framework.mics.DEFAULT
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestorePasswordViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(RestorePasswordState.DEFAULT)
        private set

    fun onEvent(event: RestorePasswordEvent) = when (event) {
        is EmailChanged -> state = state.copy(email = event.email)
        is ResetEmailButtonClicked -> state = state.copy(email = String.DEFAULT)
        is RestorePasswordButtonClicked -> { /*TODO*/
        }
    }
}