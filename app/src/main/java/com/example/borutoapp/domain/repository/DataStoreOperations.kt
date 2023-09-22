package com.example.borutoapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun sabeOnBordingState(complete: Boolean)
    fun readOnBordingState(): Flow<Boolean>

}