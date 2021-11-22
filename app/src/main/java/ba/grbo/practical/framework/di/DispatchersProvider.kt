package ba.grbo.practical.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(ActivityRetainedComponent::class)
@Module
object DispatchersProvider {
    @ActivityRetainedScoped
    @Provides
    fun provideIODispatcher() = Dispatchers.IO
}