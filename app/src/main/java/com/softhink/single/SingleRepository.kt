package com.softhink.single

import androidx.lifecycle.LiveData
import com.softhink.single.data.remote.response.UserResponse

class SingleRepository(private val dataDao: SingleDataDAO) {

    suspend fun insert(data: SingleData) {
        dataDao.insert(data)
    }

    suspend fun truncate() {
        dataDao.deleteAll()
    }

    fun getProfileFromRoom() : LiveData<List<SingleData>>{
        return dataDao.getSingleData()
    }
}