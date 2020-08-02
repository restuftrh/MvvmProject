package com.restu.mvvmproject.roomlibrary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Film::class],version = 1)
abstract class FilmDatabase : RoomDatabase() {

    abstract val filmDAO : FilmDAO

    companion object{
      @Volatile
      private var INSTANCE : FilmDatabase? = null
          fun getInstance(context: Context):FilmDatabase{
              synchronized(this){
                  var instance = INSTANCE
                      if(instance==null){
                          instance = Room.databaseBuilder(
                                 context.applicationContext,
                                 FilmDatabase::class.java,
                                 "film_data_table"
                          ).build()
                      }
                  return instance
              }
          }

    }
}

