package com.dmm.checkinstudio

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.dmm.checkinstudio.db.CheckIn
import com.dmm.checkinstudio.db.CheckInRepository
import kotlinx.coroutines.launch

class CheckInViewModel (private val repository: CheckInRepository) : ViewModel() {

    private var isUpdateOrDelete = false
    private lateinit var checkInToUpdateOrDelete: CheckIn
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    val inputToken = MutableLiveData<String>()
    val saveOrUpdateButtonText = MutableLiveData<String>()
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun saveOrUpdate() {
        if (inputName.value == null) {
            statusMessage.value = Event("Please enter subscriber's name")
        } else if (inputEmail.value == null) {
            statusMessage.value = Event("Please enter subscriber's email")
            /*} else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) {
                statusMessage.value = Event("Please enter a correct email address")*/
        }else {
            if (isUpdateOrDelete) {
                checkInToUpdateOrDelete.token = inputName.value!!
                checkInToUpdateOrDelete.datahora = inputEmail.value!!
                checkInToUpdateOrDelete.biometria = inputEmail.value!!
                updateCheckin(checkInToUpdateOrDelete)
            } else {
                val token = inputName.value!!
                val datahora = inputEmail.value!!
                val biometria = inputEmail.value!!
                insertChecKIn(CheckIn(0, token, datahora, biometria))
                inputName.value = ""
                inputEmail.value = ""
                inputToken.value = ""
            }
        }


    }

    private fun insertChecKIn(checkIn: CheckIn) = viewModelScope.launch {

        val newRowId = repository.insert(checkIn)
        if (newRowId > -1) {
            statusMessage.value = Event("Subscriber Inserted Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    private fun updateCheckin(checkIn: CheckIn) = viewModelScope.launch {
        val noOfRows = repository.update(checkIn)
        if (noOfRows > 0) {
            inputName.value = ""
            inputEmail.value = ""
            inputToken.value = ""
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRows Row Updated Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun getSavedCheckIns() = liveData {
        repository.checkIns.collect {
            emit(it)
        }
    }


    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            deleteCheckIn(checkInToUpdateOrDelete)
        } else {
            clearAll()
        }

    }


    private fun deleteCheckIn(checkIn: CheckIn) = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(checkIn)
        if (noOfRowsDeleted > 0) {
            inputName.value = ""
            inputEmail.value = ""
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRowsDeleted Row Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    private fun clearAll() = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted > 0) {
            statusMessage.value = Event("$noOfRowsDeleted Subscribers Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun initUpdateAndDelete(checkIn: CheckIn) {
        inputName.value = checkIn.token
        inputEmail.value = checkIn.token
        isUpdateOrDelete = true
        checkInToUpdateOrDelete = checkIn
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
    fun View.hideKeyboard(inputMethodManager: InputMethodManager) {
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

    fun View.setText(inputMethodManager: InputMethodManager) {
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}