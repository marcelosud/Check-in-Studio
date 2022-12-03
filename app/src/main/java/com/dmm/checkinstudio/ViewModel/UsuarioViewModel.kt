package com.dmm.checkinstudio.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.checkinstudio.entities.Usuario
import com.dmm.checkinstudio.repositories.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsuarioViewModel @Inject constructor(
    val usuarioRepository: UsuarioRepository
) : ViewModel(){

    val usuarioLiveData: LiveData<List<Usuario>> = usuarioRepository.getUsuario()

    fun insertUsuarioData(usuario: Usuario) = viewModelScope.launch {
        usuarioRepository.insertUsuarioDate(usuario)
    }
}