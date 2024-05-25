package com.example.ui.perdo

import android.widget.Button
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoadingScreen() {
    var showLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val loadingMock = 6000L

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (showLoading) {
            PedroLoading()
            LaunchedEffect(Unit) {
                coroutineScope.launch {
                    delay(loadingMock) // Delay for 1 minute
                    showLoading = false
                }
            }
        } else {
            Button(onClick = { showLoading = true }) {
                Text("Show Loading")
            }
        }
    }
}