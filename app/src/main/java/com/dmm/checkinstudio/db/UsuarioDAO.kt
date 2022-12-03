package com.dmm.checkinstudio.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dmm.checkinstudio.entities.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsuarioData(usuario: Usuario)

    @Query("SELECT * FROM usuario_data_table ORDER BY nome_id DESC")
    fun getUsuario(): LiveData<List<Usuario>>
}