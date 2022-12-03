package com.dmm.checkinstudio.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkin_data_table")
data class CheckIn(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "checkin_id")
    var id: Int,

    @ColumnInfo(name = "checkin_token")
    var token: String,

    @ColumnInfo(name = "checkin_datahora")
    var datahora: String,

    @ColumnInfo(name = "checkin_biometria")
    var biometria: String
)
