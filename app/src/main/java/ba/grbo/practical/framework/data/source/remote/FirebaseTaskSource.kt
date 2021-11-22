package ba.grbo.practical.framework.data.source.remote

import ba.grbo.core.data.TaskSource
import ba.grbo.core.domain.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FirebaseTaskSource @Inject constructor(
    source: DatabaseReference
) : TaskSource {
    private val source = source.child("tasks")
    override suspend fun add(tasks: List<Task>, uid: String): Boolean =
        suspendCancellableCoroutine { continuation ->
            source.child(uid).setValue(tasks.map { task -> task.value })
                .addOnSuccessListener { continuation.resume(true) }
                .addOnFailureListener { exception -> continuation.resumeWithException(exception) }
                .addOnCanceledListener { continuation.resume(false) }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Suppress("BlockingMethodInNonBlockingContext", "RemoveExplicitTypeArguments")
    override fun observe(uid: String) = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val tasks: List<Task> =
                        (snapshot.getValue<List<String>>() as List<String>).map {
                            Task(value = it)
                        }
                    trySendBlocking(tasks)

                } catch (e: Exception) {
                    if (e is NullPointerException) trySendBlocking(listOf<Task>())
                    else cancel(CancellationException("API error", e))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                cancel(CancellationException("API error", error.toException()))
            }
        }

        source.child(uid).addValueEventListener(listener)

        awaitClose { source.child(uid).removeEventListener(listener) }
    }
}