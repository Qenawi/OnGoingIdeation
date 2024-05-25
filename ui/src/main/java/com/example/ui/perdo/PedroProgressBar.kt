package com.example.ui.perdo


import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest


@Preview
@Composable
fun PedroLoading() {
    val context = LocalContext.current
    val imageLoader = remember { createImageLoader(context) }
    val gif = "file:///android_asset/gifs/pedro.gif"
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(gif).build(),
        imageLoader = imageLoader
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        AnimatedLoading()
    }
}

/**
 * Animated Dots
 */
@Composable
fun AnimatedDots() {
    val infiniteTransition: InfiniteTransition = rememberInfiniteTransition()
    val dotCount by infiniteTransition.animateValue(
        initialValue = 1,
        targetValue = 5,
        typeConverter = Int.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Row(Modifier.width(18.dp)) {
        for (i in 1..5) {
            Text(
                text = if (i <= dotCount) "." else "",
                fontSize = 18.sp,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

/**
 * Animated Loading , , ,
 */
@Composable
fun AnimatedLoading() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Loading", fontSize = 18.sp, style = MaterialTheme.typography.bodySmall)
            AnimatedDots()
        }
    }
}