package com.example.borutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.borutoapp.data.local.dao.HeroDao
import com.example.borutoapp.data.local.dao.HeroRemoteKeyDao
import com.example.borutoapp.domain.module.Hero
import com.example.borutoapp.domain.module.HeroRepoteKEY

@Database(entities = [Hero::class, HeroRepoteKEY::class], version = 1)
@TypeConverters(DataBaseConverter::class)
abstract class BorutoDATABASE : RoomDatabase() {
    abstract fun HeroDao(): HeroDao

    abstract fun HeroRemoteKeyDao(): HeroRemoteKeyDao
}