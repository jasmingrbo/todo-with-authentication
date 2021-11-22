package ba.grbo.practical.framework.di

import android.content.Context
import ba.grbo.core.data.DefaultTaskRepository
import ba.grbo.core.data.TaskRepository
import ba.grbo.core.data.TaskSource
import ba.grbo.practical.framework.data.source.local.PracticalDatabase
import ba.grbo.practical.framework.data.source.local.RoomTaskSource
import ba.grbo.practical.framework.data.source.remote.FirebaseTaskSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Qualifier

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class DataProvider {
    @ActivityRetainedScoped
    @Remote
    @Binds
    abstract fun bindFirebaseTasksSource(taskSource: FirebaseTaskSource): TaskSource

    @ActivityRetainedScoped
    @Local
    @Binds
    abstract fun bindRoomTasksSource(taskSource: RoomTaskSource): TaskSource

    @ActivityRetainedScoped
    @Binds
    abstract fun bindDefaultTaskRepository(taskRepository: DefaultTaskRepository): TaskRepository

    companion object {
        @ActivityRetainedScoped
        @Provides
        fun providePracticalDatabase(
            @ApplicationContext context: Context
        ) = PracticalDatabase.getInstance(context)

        @ActivityRetainedScoped
        @Provides
        fun provideTaskDao(
            database: PracticalDatabase
        ) = database.taskDao

        @ActivityRetainedScoped
        @Provides
        fun provideDefaultTaskRepository(
            @Local localTaskSource: TaskSource,
            @Remote remoteTaskSource: TaskSource
        ) = DefaultTaskRepository(localTaskSource, remoteTaskSource)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Local

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Remote