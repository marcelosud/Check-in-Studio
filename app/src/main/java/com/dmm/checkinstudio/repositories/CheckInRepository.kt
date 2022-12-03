package com.dmm.checkinstudio.repositories

import com.dmm.checkinstudio.db.CheckInDAO
import com.dmm.checkinstudio.entities.CheckIn
import javax.inject.Inject


class CheckInRepository  @Inject constructor (
    val dao: CheckInDAO)
{
    val checkIns = dao.getAllCheckIns()

    fun getCheckIn() = dao.getCheckIn()

    fun getAllCheckInEntries() = dao.getAllData()

    suspend fun insert(checkIn: CheckIn): Long {
        return dao.insertCheckIn(checkIn)
    }

    suspend fun update(checkIn: CheckIn): Int {
        return dao.updateCheckIn(checkIn)
    }

    suspend fun delete(checkIn: CheckIn): Int {
        return dao.deleteCheckIn(checkIn)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}