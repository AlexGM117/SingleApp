package com.softhink.single

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SingleDataDAO {
    @Query("SELECT * from single_table")
    fun getSingleData(): LiveData<List<SingleData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: SingleData)

    @Query("DELETE FROM single_table")
    suspend fun deleteAll()
}