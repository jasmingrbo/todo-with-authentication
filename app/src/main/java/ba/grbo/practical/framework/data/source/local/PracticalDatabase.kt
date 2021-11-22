package ba.grbo.practical.framework.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ba.grbo.practical.framework.data.dto.CacheTask


@Database(entities = [CacheTask::class], version = 1)
abstract class PracticalDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        @Volatile
        private lateinit var INSTANCE: PracticalDatabase

        private const val DB_NAME = "practical_database"

        fun getInstance(context: Context) = if (::INSTANCE.isInitialized) INSTANCE
        else synchronized(this) { buildDatabase(context).also { INSTANCE = it } }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            PracticalDatabase::class.java,
            DB_NAME
        ).build()
    }
}