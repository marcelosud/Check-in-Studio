package com.dmm.checkinstudio.ViewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dmm.checkinstudio.CheckInViewModel
import com.dmm.checkinstudio.CheckInTesteViewModel
import com.dmm.checkinstudio.db.CheckInRepository
import java.lang.IllegalArgumentException

class CheckInViewModelFactory (
    private val repository: CheckInRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CheckInViewModel::class.java)){
            return CheckInViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}
