package com.softhink.single

import androidx.lifecycle.LiveData
import androidx.room.*
import com.softhink.single.data.remote.response.UserProfile

@Dao
interface SingleDataDAO {
    @Query("SELECT * from single_table")
    fun getSingleData(): LiveData<List<SingleData>>

    @Query("UPDATE single_table SET nombre = :currName, description = :description WHERE username = :idUser")
    fun updateSingleData(currName: String, idUser: String, description: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: SingleData)

    @Query("DELETE FROM single_table")
    suspend fun deleteAll()
}