package com.example.borutoapp.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    Scaffold(topBar = { HomeTopBar(onSearcheClickd = {}) }) { innerpading ->
        Box(modifier = Modifier.padding(innerpading))

    }

}