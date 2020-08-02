package com.restu.mvvmproject.roomlibrary

import android.util.Log
import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.restu.mvvmproject.roomlibrary.db.Film
import com.restu.mvvmproject.roomlibrary.db.FilmRepository
import kotlinx.coroutines.launch


class FilmViewModel(private val repository: FilmRepository) : ViewModel(), Observable {

    val subscribers = repository.film
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Film


    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputGendre = MutableLiveData<String>()

    @Bindable
    val inputRating = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
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
            statusMessage.value = Event("Please enter film name")
        } else if (inputGendre.value == null) {
            statusMessage.value = Event("Please enter film gendre")
        } else if (inputRating.value == null) {
            statusMessage.value = Event("Please enter film rating")
        }else {
            if (isUpdateOrDelete) {
                subscriberToUpdateOrDelete.name = inputName.value!!
                subscriberToUpdateOrDelete.gendre = inputGendre.value!!
                subscriberToUpdateOrDelete.rating = inputRating.value!!
                update(subscriberToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                val gendre = inputGendre.value!!
                val rating = inputRating.value!!

                insert(Film(0, name, gendre, rating))
                inputName.value = null
                inputGendre.value = null
                inputRating.value = null
            }
        }


    }

    fun clearAllOrDelete() {
        if (isUpdateOrDelete) {
            delete(subscriberToUpdateOrDelete)
        } else {
            clearAll()
        }

    }

    fun insert(film: Film) = viewModelScope.launch {
        val newRowId = repository.insert(film)
        if (newRowId > -1) {
            statusMessage.value = Event("Film Inserted Successfully $newRowId")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun update(film: Film) = viewModelScope.launch {
        val noOfRows = repository.update(film)
        if (noOfRows > 0) {
            inputName.value = null
            inputGendre.value = null
            inputRating.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRows Row Updated Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun delete(film: Film) = viewModelScope.launch {
        val noOfRowsDeleted = repository.delete(film)

        if (noOfRowsDeleted > 0) {
            inputName.value = null
            inputGendre.value = null
            inputRating.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$noOfRowsDeleted Row Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }

    }

    fun clearAll() = viewModelScope.launch {
        val noOfRowsDeleted = repository.deleteAll()
        if (noOfRowsDeleted > 0) {
            statusMessage.value = Event("$noOfRowsDeleted Film Deleted Successfully")
        } else {
            statusMessage.value = Event("Error Occurred")
        }
    }

    fun initUpdateAndDelete(film: Film) {
        inputName.value = film.name
        inputGendre.value = film.gendre
        inputRating.value = film.gendre
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = film
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }


    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}