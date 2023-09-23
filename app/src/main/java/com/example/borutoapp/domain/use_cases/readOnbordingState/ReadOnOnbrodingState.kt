package com.example.borutoapp.domain.use_cases.readOnbordingState

import com.example.borutoapp.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnOnbrodingState(private val repository: Repository) {
    operator fun invoke(): Flow<Boolean>{
        return repository.readOnbordingState()
    }
}