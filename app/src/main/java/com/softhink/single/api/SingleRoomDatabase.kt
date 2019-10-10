package com.softhink.single.api

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.softhink.single.SingleData
import com.softhink.single.SingleDataDAO
import kotlinx.coroutines.CoroutineScope

@Database(entities = [SingleData::class], version = 1)
abstract class SingleRoomDatabase : RoomDatabase() {

    abstract fun singleDataDAO() : SingleDataDAO

    companion object {

        @Volatile
        private var INSTANCE: SingleRoomDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope): SingleRoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        SingleRoomDatabase::class.java,
                        "single_database"
                )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this codelab.
                        .fallbackToDestructiveMigration()
                        .addCallback(SingleDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class SingleDatabaseCallback(
            private val scope: CoroutineScope): RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
        }
    }
}