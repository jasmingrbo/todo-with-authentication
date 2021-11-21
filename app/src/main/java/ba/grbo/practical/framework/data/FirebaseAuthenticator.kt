package ba.grbo.practical.framework.data

import ba.grbo.core.data.Authenticator
import com.google.firebase.auth.FirebaseAuth
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

    override suspend fun logout() {
        source.signOut()
    }

    override suspend fun isLoggedIn() : Boolean = suspendCancellableCoroutine { continuation ->
        if (source.currentUser == null) continuation.resume(false)
        else source.currentUser?.reload()
            ?.addOnSuccessListener { continuation.resume(true) }
            ?.addOnFailureListener { exception -> continuation.resumeWithException(exception) }
            ?.addOnCanceledListener { continuation.resume(false) }
    }
}