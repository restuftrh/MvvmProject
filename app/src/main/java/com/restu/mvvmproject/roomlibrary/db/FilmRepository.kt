package com.restu.mvvmproject.roomlibrary.db

class FilmRepository(private val dao : FilmDAO) {

    val film = dao.getAllFilm()

    suspend fun insert(film: Film):Long{
       return dao.insertFilm(film)
    }

    suspend fun update(film: Film):Int{
        return dao.updateFilm(film)
    }

    suspend fun delete(film: Film) : Int{
       return dao.deleteFilm(film)
    }

    suspend fun deleteAll() : Int{
        return dao.deleteAll()
    }
}