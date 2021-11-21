package ba.grbo.practical.framework.di

import ba.grbo.core.data.Authenticator
import ba.grbo.practical.framework.data.FirebaseAuthenticator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class AuthenticatorProvider {
    @ActivityRetainedScoped
    @Binds
    abstract fun bindFirebaseAuthenticator(authenticator: FirebaseAuthenticator): Authenticator
}