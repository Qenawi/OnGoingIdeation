package com.example.ui.telda

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DraggableItem(
    modifier: Modifier = Modifier,
    areaBounds: Rect?,
    parentBounds: Rect?,
    isError: Boolean,
    onDrag: (Offset) -> Unit,
    onDrop: (Offset) -> Unit,

    ) {
    var offsetX by remember { mutableStateOf(0f) }
    var offsetY by remember { mutableStateOf(0f) }
    val animationSpec: AnimationSpec<Float> = tween(durationMillis = 10, delayMillis = 0, easing = FastOutLinearInEasing)
    val animatedOffsetX: Float by animateFloatAsState(
        targetValue = offsetX,
        label = "",
        animationSpec = animationSpec
    )
    val animatedOffsetY: Float by animateFloatAsState(
        targetValue = offsetY,
        label = "",
        animationSpec = animationSpec
    )
    var itemSize by remember { mutableStateOf(IntSize.Zero) }
    var isDragging by remember { mutableStateOf(false) }
    val horizontalDraggableState = rememberDraggableState { delta ->
        offsetX = (offsetX + delta).coerceIn(
            0f,
            (parentBounds?.width ?: 0f) - itemSize.width.toFloat()
        )
    }

    val verticalDraggableState = rememberDraggableState { delta ->
        offsetY = (offsetY + delta).coerceIn(
            0f,
            (parentBounds?.height ?: 0f) - itemSize.height.toFloat()
        )
    }
    val isInBounds = areaBounds?.let { bounds ->
        offsetX >= bounds.left &&
                offsetX <= bounds.right &&
                offsetY >= bounds.top &&
                offsetY <= bounds.bottom
    } ?: false


    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                itemSize = coordinates.size
            }
            .offset { IntOffset(animatedOffsetX.roundToInt(), animatedOffsetY.roundToInt()) }
            .background(
                if (isError) Color.Red else if (isInBounds) Color.Green else Color.Black,
                CircleShape
            )
            .draggable(
                orientation = Orientation.Horizontal,
                state = horizontalDraggableState,
                onDragStarted = {
                    isDragging = true


                    parentBounds?.let { bounds ->
                        val absolutePosition = Offset(
                            offsetX + bounds.left,
                            offsetY + bounds.top
                        )
                        onDrag(absolutePosition)
                    }

                },
                onDragStopped = {
                    isDragging = false
                    parentBounds?.let { bounds ->
                        val absolutePosition = Offset(
                            offsetX + bounds.left,
                            offsetY + bounds.top
                        )
                        onDrop(absolutePosition)
                    }
                }
            )
            .draggable(
                orientation = Orientation.Vertical,
                state = verticalDraggableState,
                onDragStarted = { isDragging = true

                    parentBounds?.let { bounds ->
                        val absolutePosition = Offset(
                            offsetX + bounds.left,
                            offsetY + bounds.top
                        )
                        onDrag(absolutePosition)
                    }

                                },
                onDragStopped = {
                    isDragging = false
                    parentBounds?.let { bounds ->
                        val absolutePosition = Offset(
                            offsetX + bounds.left,
                            offsetY + bounds.top
                        )
                        onDrop(absolutePosition)
                    }
                }
            )
            .zIndex(if (isDragging) 1f else 0f)
    ) {
        Text(
            " ${offsetY.dp}, ${offsetX.dp}",
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center),
            color = Color.White
        )
    }
}