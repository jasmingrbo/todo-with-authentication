package ba.grbo.practical.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import ba.grbo.core.interactors.AuthenticateWithGoogle
import ba.grbo.practical.framework.data.GoogleAuthentication
import ba.grbo.practical.framework.mics.AuthenticateWithGoogleActivityResultContract
import ba.grbo.practical.framework.mics.SingleSharedFlow
import ba.grbo.practical.presentation.composables.PracticalApp
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("PropertyName")
@AndroidEntryPoint
class PracticalActivity : ComponentActivity() {

    lateinit var viewModel: PracticalViewModel

    @Inject
    lateinit var authenticateWithGoogle: AuthenticateWithGoogle

    @Inject
    lateinit var IODispatcher: CoroutineDispatcher

    @Inject
    lateinit var gsc: GoogleSignInClient

    @Inject
    lateinit var resetGoogleAuthProcess: () -> Unit

    private lateinit var authWithGoogle: ActivityResultLauncher<Intent>

    val googleAuthStatus = SingleSharedFlow<GoogleAuthentication.Status>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        authWithGoogle = registerForActivityResult(
            AuthenticateWithGoogleActivityResultContract()
        ) { task ->

            task
                .addOnSuccessListener {
                    lifecycleScope.launch(IODispatcher) {
                        try {
                            val authed = authenticateWithGoogle(it.idToken!!)
                            if (authed.first) onGoogleAuthSucceeded(authed.second)
                            else onGoogleAuthFailed(Exception())
                        } catch (e: Exception) {
                            onGoogleAuthFailed(e)
                        }
                    }
                }
                .addOnFailureListener { onGoogleAuthFailed(it) }
                .addOnCanceledListener { onGoogleAuthFailed(Exception()) }
        }

        setContent {
            viewModel = hiltViewModel()
            PracticalApp(authenticated = viewModel.authenticated)
        }
    }

    fun fireAuthWithGoogle() {
        authWithGoogle.launch(gsc.signInIntent)
    }

    private fun onGoogleAuthFailed(e: Exception) {
        resetGoogleAuthProcess() // So that the menu for choosing account is shown again
        googleAuthStatus.tryEmit(GoogleAuthentication.Status.Failed(e))
    }

    private fun onGoogleAuthSucceeded(isNew: Boolean) {
        googleAuthStatus.tryEmit(GoogleAuthentication.Status.Succeeded(isNew))
    }
}