package com.example.borutoapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.borutoapp.domain.module.HeroRepoteKEY

@Dao
interface HeroRemoteKeyDao {
    @Query("SELECT*FROM hero_remote_key_table WHERE id =:id")
    suspend fun getRemoteKey(id: Int): HeroRepoteKEY?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddAllRemoteKey(heroRepoteKEY: List<HeroRepoteKEY>)

    @Query("DELETE FROM hero_remote_key_table ")
    suspend fun deleteAllRemoteKey()
}