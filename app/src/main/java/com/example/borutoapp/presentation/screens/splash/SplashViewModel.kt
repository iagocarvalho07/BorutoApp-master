package com.example.borutoapp.presentation.screens.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoapp.domain.use_cases.UsesCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val usesCases: UsesCases
) : ViewModel() {

    private val _onBordingState = MutableStateFlow(false)
    val onbrodingState: StateFlow<Boolean> = _onBordingState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBordingState.value = usesCases.readOnOnbrodingState().stateIn(viewModelScope).value
        }
    }
}