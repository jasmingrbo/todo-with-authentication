package ba.grbo.practical.framework.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class CacheTask(
    // @PrimaryKey(autoGenerate = true)
    // val id: Long = 0,
    @PrimaryKey
    val value: String,
    val uid: String,
)
