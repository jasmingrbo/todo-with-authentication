package ba.grbo.practical.framework.data

import ba.grbo.core.data.Authenticator
import ba.grbo.core.domain.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseAuthenticator @Inject constructor(
    private val source: FirebaseAuth
) : Authenticator {
    override suspend fun login(
        email: String,
        password: String
    ): Boolean = suspendCancellableCoroutine { continuation ->
        source.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { continuation.resume(true) }
            .addOnFailureListener { exception -> continuation.resumeWithException(exception) }
            .addOnCanceledListener { continuation.resume(false) }
    }

    override suspend fun signUp(
        email: String,
        password: String
    ): Boolean = suspendCancellableCoroutine { continuation ->
        source.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { continuation.resume(true) }
            .addOnFailureListener { exception -> continuation.resumeWithException(exception) }
            .addOnCanceledListener { continuation.resume(false) }
    }

    override fun logout() {
        source.signOut()
    }

    override suspend fun isLoggedIn() : Boolean = suspendCancellableCoroutine { continuation ->
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