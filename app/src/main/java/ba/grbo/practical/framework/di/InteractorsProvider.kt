package ba.grbo.practical.framework.di

import ba.grbo.core.data.Authenticator
import ba.grbo.core.data.TaskRepository
import ba.grbo.core.interactors.AddTasks
import ba.grbo.core.interactors.AuthenticateWithGoogle
import ba.grbo.core.interactors.CreateUser
import ba.grbo.core.interactors.DefaultAddTasks
import ba.grbo.core.interactors.DefaultAuthenticateWithGoogle
import ba.grbo.core.interactors.DefaultCreateUser
import ba.grbo.core.interactors.DefaultFetchUser
import ba.grbo.core.interactors.DefaultIsAuthenticated
import ba.grbo.core.interactors.DefaultLoginUser
import ba.grbo.core.interactors.DefaultObserveAuthentication
import ba.grbo.core.interactors.DefaultObserveTasks
import ba.grbo.core.interactors.DefaultSignOutUser
import ba.grbo.core.interactors.FetchUser
import ba.grbo.core.interactors.IsAuthenticated
import ba.grbo.core.interactors.LoginUser
import ba.grbo.core.interactors.ObserveAuthentication
import ba.grbo.core.interactors.ObserveTasks
import ba.grbo.core.interactors.SignOutUser
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
        fun provideDefaultLoginUserInteractor(
            authenticator: Authenticator
        ) = DefaultLoginUser(authenticator)

        @ActivityRetainedScoped
        @Provides
        fun provideDefaultCreateUserInteractor(
            authenticator: Authenticator
        ) = DefaultCreateUser(authenticator)

        @ActivityRetainedScoped
        @Provides
        fun provideDefaultIsAuthenticatedInteractor(
            authenticator: Authenticator
        ) = DefaultIsAuthenticated(authenticator)

        @ActivityRetainedScoped
        @Provides
        fun provideDefaultObserveTasksInteractor(
            taskRepository: TaskRepository
        ) = DefaultObserveTasks(taskRepository)

        @ActivityRetainedScoped
        @Provides
        fun provideDefaultAddTasksInteractor(
            taskRepository: TaskRepository
        ) = DefaultAddTasks(taskRepository)

        @ActivityRetainedScoped
        @Provides
        fun provideDefaultFetchUserInteractor(
            authenticator: Authenticator
        ) = DefaultFetchUser(authenticator)

        @ActivityRetainedScoped
        @Provides
        fun provideDefaultSignOutUserInteractor(
            authenticator: Authenticator
        ) = DefaultSignOutUser(authenticator)

        @ActivityRetainedScoped
        @Provides
        fun provideDefaultObserveAuthenticationInteractor(
            authenticator: Authenticator
        ) = DefaultObserveAuthentication(authenticator)

        @ActivityRetainedScoped
        @Provides
        fun provideDefaultAuthenticateWithGoogleInteractor(
            authenticator: Authenticator
        ) = DefaultAuthenticateWithGoogle(authenticator)
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

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDefaultObserveTasksInteractor(
        observeTasks: DefaultObserveTasks
    ): ObserveTasks

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDefaultAddTasksInteractor(
        addTasks: DefaultAddTasks
    ): AddTasks

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDefaultFetchUserInteractor(
        fetchUser: DefaultFetchUser
    ): FetchUser

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDefaultSignOutUserInteractor(
        signOutUser: DefaultSignOutUser
    ): SignOutUser

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDefaultObserveAuthenticationInteractor(
        observeAuthentication: DefaultObserveAuthentication
    ): ObserveAuthentication

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDefaultAuthenticateWithGoogleInteractor(
        googleSignIn: DefaultAuthenticateWithGoogle
    ): AuthenticateWithGoogle
}