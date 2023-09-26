package com.example.borutoapp.presentation.comom

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.paging.LoadState
import com.example.borutoapp.R
import com.example.borutoapp.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.example.borutoapp.ui.theme.SMALL_PADDING

@Composable
fun EmptyScreen(error: LoadState.Error) {
    val message by remember { mutableStateOf(parseErrorMessage(message = error.toString())) }
    val icon by remember {
        mutableStateOf(R.drawable.ic_network_error)
    }

    var startAnimatione by remember {
        mutableStateOf(false)
    }
    val alphaName by animateFloatAsState(
        targetValue = if (startAnimatione) ContentAlpha.disabled else 0f,
        animationSpec = tween(durationMillis = 1000), label = ""
    )
    LaunchedEffect(key1 = true) {
        startAnimatione = true
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        androidx.compose.material.Icon(
            modifier = Modifier
                .size(NETWORK_ERROR_ICON_HEIGHT)
                .alpha(alpha = alphaName),
            painter = painterResource(id = icon),
            contentDescription = "NetWork Error icon",
            tint = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray
        )
        Text(
            modifier = Modifier.padding(top = SMALL_PADDING),
            text = message,
            color = if (isSystemInDarkTheme()) Color.LightGray else Color.DarkGray,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )

    }
}


fun parseErrorMessage(message: String): String {
    return when {
        message.contains("SocketTimeoutException") -> {
            "Server Unavailable"
        }

        message.contains("ConnectException") -> {
            "Internet  Unavailable"
        }

        else -> {
            "UnKnown Error"
        }
    }
}