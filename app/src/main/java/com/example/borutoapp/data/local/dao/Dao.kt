package com.example.borutoapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.borutoapp.domain.module.Hero

@Dao
interface HeroDao {
    @Query("SELECT * FROM herotable ORDER BY id ASC")
    fun getAllHeroes(): PagingSource<Int, Hero>

    @Query("SELECT *FROM herotable WHERE id =:heroId")
    fun getSelectHero(heroId: Int): Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun adHeros(Heroes: List<Hero>)

    @Query("DELETE FROM herotable")
    suspend fun deleteAllHeros()
}