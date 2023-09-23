package com.example.borutoapp.data.repository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
) {
    suspend fun saveOnBordingState(complet: Boolean) {
        dataStoreOperations.sabeOnBordingState(complete = complet)
    }

    fun readOnbordingState(): Flow<Boolean> {
        return dataStoreOperations.readOnBordingState()
    }
}