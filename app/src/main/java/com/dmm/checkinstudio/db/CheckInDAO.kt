package com.dmm.checkinstudio.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dmm.checkinstudio.entities.CheckIn
import com.dmm.checkinstudio.entities.Usuario
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

    @Query("SELECT * FROM checkin_data_table ORDER BY checkin_token DESC, checkin_datahora DESC")
    fun getCheckIn(): LiveData<List<CheckIn>>

    @Query("SELECT * FROM checkin_data_table ORDER BY checkin_token DESC, checkin_datahora DESC")
    fun getAllData():LiveData<List<CheckIn>>
}