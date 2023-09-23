package com.example.borutoapp.data.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun sabeOnBordingState(complete: Boolean)
    fun readOnBordingState(): Flow<Boolean>

}