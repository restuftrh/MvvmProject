package com.restu.mvvmproject.roomlibrary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.restu.mvvmproject.roomlibrary.db.FilmRepository
import java.lang.IllegalArgumentException

class FilmViewModelFactory(private val repository: FilmRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
     if(modelClass.isAssignableFrom(FilmViewModel::class.java)){
         return FilmViewModel(repository) as T
     }
        throw IllegalArgumentException("Unknown View Model class")
    }

}