package com.example.borutoapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.example.borutoapp.domain.use_cases.UsesCases
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    userCases: UsesCases
) : ViewModel(){
    val getAllHeros = userCases.getAllHeroesUseCases()

}