package com.softhink.single

import androidx.lifecycle.LiveData

class SingleRepository(private val dataDao: SingleDataDAO) {

    val singleData: LiveData<List<SingleData>> = dataDao.getSingleData()


    suspend fun insert(data: SingleData) {
        dataDao.insert(data)
    }

    suspend fun truncate() {
        dataDao.deleteAll()
    }
}