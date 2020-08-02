package com.restu.mvvmproject.roomlibrary.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film_data_table")
data class Film (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "film_id")
    var id : Int,

    @ColumnInfo(name = "film_name")
    var name : String,

    @ColumnInfo(name = "film_gendre")
    var gendre : String,

    @ColumnInfo(name = "film_rating")
    var rating : String

)