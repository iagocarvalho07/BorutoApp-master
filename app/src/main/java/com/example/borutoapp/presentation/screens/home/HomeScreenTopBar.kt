package com.example.borutoapp.presentation.screens.home

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import com.example.borutoapp.ui.theme.topAppBarBackgroundColor
import com.example.borutoapp.ui.theme.topAppBarContentColor
import org.w3c.dom.Text

@Composable
fun HomeTopBar(onSearcheClickd: () -> Unit = {}) {
    TopAppBar(
        title = { Text(text = "Explore", color = MaterialTheme.colors.topAppBarContentColor) },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            IconButton(onClick = onSearcheClickd) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            }
        }
    )
}