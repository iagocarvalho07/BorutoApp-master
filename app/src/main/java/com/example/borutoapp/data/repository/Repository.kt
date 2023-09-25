package com.example.borutoapp.data.repository

import androidx.paging.PagingData
import com.example.borutoapp.domain.module.Hero
import com.example.borutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote:  RemoteDataSource,
    private val dataStoreOperations: DataStoreOperations
) {

    fun getallHeroes(): Flow<PagingData<Hero>>{
        return remote.getAllData()
    }
    suspend fun saveOnBordingState(complet: Boolean) {
        dataStoreOperations.sabeOnBordingState(complete = complet)
    }

    fun readOnbordingState(): Flow<Boolean> {
        return dataStoreOperations.readOnBordingState()
    }
}