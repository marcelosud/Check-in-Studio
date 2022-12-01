package com.dmm.checkinstudio.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckInDAO {

    @Insert
    suspend fun insertCheckIn(checkIn: CheckIn) : Long

    @Update
    suspend fun updateCheckIn(checkIn: CheckIn) : Int

    @Delete
    suspend fun deleteCheckIn(checkIn: CheckIn) : Int

    @Query("DELETE FROM checkin_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM checkin_data_table")
    fun getAllCheckIns(): Flow<List<CheckIn>>
}