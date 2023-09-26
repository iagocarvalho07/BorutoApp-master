package com.example.borutoapp.domain.module

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.borutoapp.util.Constants.HERO_REMOTE_KEY_DATABASE_TABLE

@Entity(tableName = HERO_REMOTE_KEY_DATABASE_TABLE)
data class HeroRepoteKEY(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prepage: Int?,
    val nextpage: Int?,
    val lastUpdated: Long?

)
