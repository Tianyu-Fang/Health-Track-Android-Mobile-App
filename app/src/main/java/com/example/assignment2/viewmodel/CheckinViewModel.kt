package com.example.assignment2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment2.model.Checkin
import com.example.assignment2.repository.CheckinRepository

class CheckinViewModel(private val repository: CheckinRepository) : ViewModel() {

    var _checkindata: LiveData<List<Checkin>> = MutableLiveData()
    var _newCheckindata: LiveData<Checkin> = MutableLiveData()
    val allRecords: LiveData<List<Checkin>>
        get() = _checkindata
    val newRecord: LiveData<Checkin>
        get() = _newCheckindata

    init {
//        viewModelScope.launch {
        getAllCheckin()
        getNewCheckin()
//        }

    }

    // insert note
    fun addCheckin(checkindata: Checkin) {
        repository.addCheckin(checkindata)
    }

    fun getAllCheckin() {
        _checkindata = repository.getAllCheckin()
    }

    fun getNewCheckin() {
        _newCheckindata = repository.getNewCheckin()
    }


//    // update
//    suspend fun updateNote(note: Note) {
//        repository.updateNote(note)
//    }
//
//    // delete note
//    suspend fun deleteNote(note: Note) {
//        repository.deleteNote(note)
//    }
}

class CheckinViewModelFactory(private val repository: CheckinRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CheckinViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CheckinViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
