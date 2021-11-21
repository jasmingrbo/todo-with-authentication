package ba.grbo.practical.framework.di

import ba.grbo.core.data.Authenticator
import ba.grbo.core.interactors.CreateUser
import ba.grbo.core.interactors.DefaultCreateUser
import ba.grbo.core.interactors.DefaultIsAuthenticated
import ba.grbo.core.interactors.DefaultLoginUser
import ba.grbo.core.interactors.IsAuthenticated
import ba.grbo.core.interactors.LoginUser
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class InteractorsProvider {
    companion object {
        @ActivityRetainedScoped
        @Provides
        fun provideLoginUserInteractor(
            authenticator: Authenticator
        ) = DefaultLoginUser(authenticator)

        @ActivityRetainedScoped
        @Provides
        fun provideCreateUserInteractor(
            authenticator: Authenticator
        ) = DefaultCreateUser(authenticator)

        @ActivityRetainedScoped
        @Provides
        fun provideIsAuthenticatedInteractor(
            authenticator: Authenticator
        ) = DefaultIsAuthenticated(authenticator)
    }

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDefaultLoginUserInteractor(loginUser: DefaultLoginUser): LoginUser

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDefaultCreateUserInteractor(createUser: DefaultCreateUser): CreateUser

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDefaultIsAuthenticatedInteractor(
        isAuthenticated: DefaultIsAuthenticated
    ): IsAuthenticated
}