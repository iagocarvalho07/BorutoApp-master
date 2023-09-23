package com.example.borutoapp.data.local

import androidx.room.TypeConverter

class DataBaseConverter {

    private val separator = ","
    @TypeConverter
    fun convertListToString(list: List<String>): String = list.joinToString(separator = ", ")
    @TypeConverter
    fun convertStringToList(string: String): List<String> = string.split(separator)
}