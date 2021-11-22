package ba.grbo.practical.framework.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(ActivityRetainedComponent::class)
@Module
object FirebaseProvider {
    @ActivityRetainedScoped
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @ActivityRetainedScoped
    @Provides
    fun provideFirebaseDb() = Firebase
        .database("https://practical-255d8-default-rtdb.europe-west1.firebasedatabase.app/")
        .reference
}