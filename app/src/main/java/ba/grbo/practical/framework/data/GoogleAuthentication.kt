package ba.grbo.practical.framework.data

import ba.grbo.core.domain.Task
import ba.grbo.practical.R
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.random.Random

interface GoogleAuthentication {
    val googleAuthAttempt: MutableSharedFlow<Unit>

    fun onGoogleAuthFailed(exception: Exception)

    fun onGoogleAuthSucceeded(isNew: Boolean)

    fun getFeedback(exception: Exception) = when (exception) {
        is ApiException -> when (exception.statusCode) {
            GoogleSignInStatusCodes.SIGN_IN_CANCELLED -> R.string.google_authentication_failed_cancelled
            GoogleSignInStatusCodes.NETWORK_ERROR -> R.string.google_authentication_failed_network
            else -> R.string.google_authentication_failed_generic
        }
        is TimeoutCancellationException -> R.string.google_authentication_failed_network
        else -> R.string.google_authentication_failed_generic
    }

    fun getRandomFiveDummyTasks(): List<Task> {
        val dummyTasks = listOf(
            Task("Learn Jetpack Compose"),
            Task("Clean the house"),
            Task("Go shopping"),
            Task("Pay the bills"),
            Task("Attend a meeting"),
            Task("Help those in need"),
            Task("Visit mom"),
            Task("Call a friend"),
            Task("Finish the app"),
            Task("Learn some German"),
            Task("Practice English"),
            Task("Watch an animal documentary"),
            Task("Make the dinner"),
            Task("Go for a walk")
        )

        val randomInts = mutableListOf<Int>()

        for (i in 1..5) {
            var randomInt = Random.nextInt(0, 14)
            while (randomInt in randomInts) randomInt = Random.nextInt(0, 14)
            randomInts.add(randomInt)
        }

        return randomInts.map { index -> dummyTasks[index] }
    }


    sealed interface Status {
        class Succeeded (val isNew: Boolean): Status
        class Failed(val exception: Exception): Status
    }
}