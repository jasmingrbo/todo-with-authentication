package ba.grbo.practical.framework.data

import android.util.Log
import ba.grbo.core.data.Authenticator
import ba.grbo.core.domain.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseAuthenticator @Inject constructor(
    private val source: FirebaseAuth,
    private val signOutOfGoogle: () -> Unit,
) : Authenticator {
    private val timeOut = 5000L
    private var googleSignedIn = false

    override suspend fun login(
        email: String,
        password: String
    ): Boolean = withTimeout(timeOut) {
        suspendCancellableCoroutine { continuation ->
            source.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { continuation.resume(true) }
                .addOnFailureListener { exception -> continuation.resumeWithException(exception) }
                .addOnCanceledListener { continuation.resume(false) }
        }
    }

    override suspend fun googleSignIn(
        idToken: String
    ): Pair<Boolean, Boolean> = withTimeout(timeOut) {
        suspendCancellableCoroutine { continuation ->
            source.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
                .addOnSuccessListener { result ->
                    val isNewUser = result.additionalUserInfo?.isNewUser ?: false
                    googleSignedIn = true
                    continuation.resume(true to isNewUser)
                }
                .addOnFailureListener { exception -> continuation.resumeWithException(exception) }
                .addOnCanceledListener { continuation.resume(false to false) }
        }
    }
    override suspend fun signUp(
        email: String,
        password: String
    ): Boolean = withTimeout(timeOut) {
        suspendCancellableCoroutine { continuation ->
            source.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener { continuation.resume(true) }
                .addOnFailureListener { exception -> continuation.resumeWithException(exception) }
                .addOnCanceledListener { continuation.resume(false) }
        }
    }

    override fun logout() {
        if (googleSignedIn) signOutOfGoogle()
        source.signOut()
    }

    override suspend fun isLoggedIn(): Boolean = suspendCancellableCoroutine { continuation ->
        if (source.currentUser == null) continuation.resume(false)
        else source.currentUser?.reload()
            ?.addOnSuccessListener { continuation.resume(true) }
            ?.addOnFailureListener { exception -> continuation.resumeWithException(exception) }
            ?.addOnCanceledListener { continuation.resume(false) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun observeAuthenticationStatus() = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            trySendBlocking(auth.currentUser != null)
        }

        source.addAuthStateListener(listener)

        awaitClose { source.removeAuthStateListener(listener) }
    }

    override fun getUser(): User = User(
        uid = source.currentUser!!.uid,
        email = source.currentUser!!.email!!
    )
}