package com.example.borutoapp.domain.use_cases.save_onBordingState

import com.example.borutoapp.data.repository.Repository

class SaveOnbordingState(private val repository: Repository) {

    suspend operator fun invoke(complete: Boolean){
        repository.saveOnBordingState(complete)
    }
}