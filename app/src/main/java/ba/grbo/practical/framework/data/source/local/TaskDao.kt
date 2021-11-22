package ba.grbo.practical.framework.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import ba.grbo.practical.framework.data.dto.CacheTask
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(tasks: List<CacheTask>)

    @Query("SELECT * FROM tasks_table WHERE uid == :uid")
    fun observe(uid: String): Flow<List<CacheTask>>

    @Query("DELETE FROM tasks_table")
    suspend fun clear()
}