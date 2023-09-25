package com.example.borutoapp.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.presentation.comom.ListContent

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavController) {

    val allHeros = homeViewModel.getAllHeros.collectAsLazyPagingItems()

    Scaffold(topBar = { HomeTopBar(onSearcheClickd = {}) }) { innerpading ->
        Box(modifier = Modifier.padding(innerpading)) {
            ListContent(heros = allHeros, navController = navController)
        }

    }
}