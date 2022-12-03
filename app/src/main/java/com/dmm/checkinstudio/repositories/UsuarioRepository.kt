package com.dmm.checkinstudio.repositories

import com.dmm.checkinstudio.db.UsuarioDAO
import com.dmm.checkinstudio.entities.Usuario
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    val usuarioDao : UsuarioDAO
){

    fun getUsuario() = usuarioDao.getUsuario()

    suspend fun insertUsuarioDate(usuario:Usuario) = usuarioDao.insertUsuarioData(usuario)
}