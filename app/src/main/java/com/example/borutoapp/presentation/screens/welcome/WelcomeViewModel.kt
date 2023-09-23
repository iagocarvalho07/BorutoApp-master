package com.example.borutoapp.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoapp.domain.use_cases.UsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val userCases: UsesCases
) : ViewModel() {
    fun saveOnBordingState(complete: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            userCases.saveOnbordingState(complete)
        }
    }
}