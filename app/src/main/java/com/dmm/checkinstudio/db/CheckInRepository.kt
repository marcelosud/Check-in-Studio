package com.dmm.checkinstudio.db


class CheckInRepository (private val dao: CheckInDAO) {

    val checkIns = dao.getAllCheckIns()

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