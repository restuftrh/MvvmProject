package com.restu.mvvmproject.roomlibrary.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FilmDAO {

    @Insert
    suspend fun insertFilm(film: Film) : Long

    @Update
    suspend fun updateFilm(film: Film) : Int

    @Delete
    suspend fun deleteFilm(film: Film) : Int

    @Query("DELETE FROM film_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM film_data_table")
    fun getAllFilm():LiveData<List<Film>>
}