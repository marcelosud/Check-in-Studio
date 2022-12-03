package com.dmm.checkinstudio.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario_data_table")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "nome_id")
    var id: Int,

    @ColumnInfo(name = "nome")
    var nome: String,

    @ColumnInfo(name = "documento")
    var documento: String,

    @ColumnInfo(name = "token")
    var token: String
)
