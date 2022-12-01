package com.dmm.checkinstudio.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CheckIn::class], version = 1)
abstract class CheckInDatabase : RoomDatabase() {
    abstract val checkInDAO: CheckInDAO

    companion object {
        @Volatile
        private var INSTANCE: CheckInDatabase? = null
        fun getInstance(context: Context): CheckInDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CheckInDatabase::class.java,
                        "checkin_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}
