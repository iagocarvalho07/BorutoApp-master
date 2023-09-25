package com.example.borutoapp.domain.repository

import androidx.paging.PagingData
import com.example.borutoapp.domain.module.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllData(): Flow<PagingData<Hero>>
    fun serachHeroes():Flow<PagingData<Hero>>
}