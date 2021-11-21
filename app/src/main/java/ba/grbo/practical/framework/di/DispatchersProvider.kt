package ba.grbo.practical.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DispatchersProvider {
    @Singleton
    @Provides
    fun provideIODispatcher() = Dispatchers.IO
}