package com.dmm.checkinstudio.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmm.checkinstudio.CheckInViewModel
import com.dmm.checkinstudio.entities.CheckIn
import com.dmm.checkinstudio.entities.Usuario
import com.dmm.checkinstudio.repositories.CheckInRepository
import com.dmm.checkinstudio.repositories.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RelatorioViewModel @Inject constructor(
    val checkInRepository: CheckInRepository
    //private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel(){

    //val checkInLiveData: LiveData<List<CheckIn>> = checkInRepository.getCheckIn()


    private val _listaCheckInFlow = MutableStateFlow<List<CheckIn>>(listOf())
    val listaCheckInFlow: StateFlow<List<CheckIn>> = _listaCheckInFlow

/*    fun listarAlunos() {
        viewModelScope.launch(dispatcher) {
            checkInRepository.getCheckIn()
        }
    }*/

    val allCheckInEntries : LiveData<List<CheckIn>> = checkInRepository.getAllCheckInEntries()

    fun listarCheckIn() {
            checkInRepository.getCheckIn()
    }
}