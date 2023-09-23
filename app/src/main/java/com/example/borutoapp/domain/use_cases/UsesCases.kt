package com.example.borutoapp.domain.use_cases

import com.example.borutoapp.domain.use_cases.readOnbordingState.ReadOnOnbrodingState
import com.example.borutoapp.domain.use_cases.save_onBordingState.SaveOnbordingState

data class UsesCases(
    val saveOnbordingState: SaveOnbordingState,
    val readOnOnbrodingState: ReadOnOnbrodingState
)
