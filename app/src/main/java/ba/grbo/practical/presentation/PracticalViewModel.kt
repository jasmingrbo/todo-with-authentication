package ba.grbo.practical.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ba.grbo.core.interactors.IsAuthenticated
import ba.grbo.core.interactors.ObserveAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PracticalViewModel @Inject constructor(
    isAuthenticated: IsAuthenticated,
    observeAuthentication: ObserveAuthentication,
    IODispatcher: CoroutineDispatcher
) : ViewModel() {
    var loggedIn by mutableStateOf<Boolean?>(null)
        private set

    init {
        viewModelScope.launch {
            loggedIn = withContext(IODispatcher) {
                try {
                    isAuthenticated()
                } catch (e: Exception) {
                    // TODO Log the exception
                    false
                }
            }
        }

        observeAuthentication()
            .onEach { authenticated -> if (loggedIn != authenticated) loggedIn = authenticated }
            .launchIn(viewModelScope)
    }
}