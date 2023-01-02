package com.example.bookapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.paging.LoadState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookapp.R
import com.example.bookapp.ui.theme.*
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(error: LoadState.Error? = null) {

    var message by remember {
        mutableStateOf("Find the topic you want!")
    }

    if (error != null) {
        message = parseErrorMessage(error = error)
    }

    val icon by remember {
        mutableStateOf(
            when (message) {
                "Find the topic you want!" -> R.drawable.searchicon
                "Not Internet" -> R.drawable.nointernet
                "Server Unavailable." -> R.drawable.serverunavailable
                else -> R.drawable.no
            }
        )
    }


    var starAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (starAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 2000
        )
    )

    LaunchedEffect(key1 = true) {
        starAnimation = true
    }

    EmptyContent(alphaAnim = alphaAnim, icon = icon, message = message)

}


@Composable
fun EmptyContent(alphaAnim: Float, icon: Int, message: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .size(NETWORK_ERROR_ICON_HEIGHT)
                .alpha(alpha = alphaAnim),
            painter = painterResource(id = icon),
            tint = MaterialTheme.colors.ErrorsPlaceholders,
            contentDescription = stringResource(R.string.network_error)
        )
        Text(
            modifier = Modifier
                .padding(top = SMALL_PADDING)
                .alpha(alpha = alphaAnim),
            text = message,
            color = MaterialTheme.colors.ErrorsPlaceholders,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}


fun parseErrorMessage(error: LoadState.Error): String {
    return when (error.error) {
        is SocketTimeoutException -> {
            "Server Unavailable."
        }
        is ConnectException -> {
            "Not Internet"
        }
        else -> {
            "Unknown Error"
        }
    }
}

@Preview(showBackground = true)
@Composable
fun empty() {
    EmptyContent(alphaAnim = 1f, icon = R.drawable.nointernet, message = "No internet")
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun empty2() {
    EmptyContent(alphaAnim = 1f, icon = R.drawable.nointernet, message = "No internet")
}