package com.berezhnoyyuri9999.phonebookmvvmhiltflow.data

import androidx.room.*
import com.berezhnoyyuri9999.phonebookmvvmhiltflow.data.preference.SortOrder
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    fun getTasks(query: String, sortOrder: SortOrder, hideCompleted: Boolean): Flow<List<Task>> =
         when(sortOrder) {
            SortOrder.BY_DATE -> getTasksSortedByDateCreated(query, hideCompleted)
            SortOrder.BY_NAME -> getTasksSortedByName(query, hideCompleted)
    }

    @Query("Select * from task_table where (completed != :hideCompleted OR completed = 0) AND name like '%' || :searchQuery || '%' order by important DESC, name")
    fun getTasksSortedByName(searchQuery: String, hideCompleted: Boolean): Flow<List<Task>>

    @Query("Select * from task_table where (completed != :hideCompleted OR completed = 0) AND name like '%' || :searchQuery || '%' order by important DESC, created")
    fun getTasksSortedByDateCreated(searchQuery: String, hideCompleted: Boolean): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("Delete from task_table where completed = 1")
    suspend fun deleteCompletedTasks()

}