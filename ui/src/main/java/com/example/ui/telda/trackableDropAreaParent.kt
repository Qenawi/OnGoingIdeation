package com.example.ui.telda

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt


@Preview(showBackground = true, name = "DropArea")
@Composable
fun DropArea(
    modifier: Modifier = Modifier,
    cardColor: Color = Color.LightGray,
    children: List<ItemType> = emptyList()
) {
    val colorChangeAnimationSpec = tween<Color>(1000, easing = FastOutSlowInEasing)
    val cardColorStatus by animateColorAsState(
        cardColor,
        animationSpec = colorChangeAnimationSpec,
        label = "Card Color"
    )
    Column(
        modifier =
        modifier
            .background(Color.White)
            .wrapContentSize()
    )
    {
        Box(
            modifier = Modifier
                .size(300.dp, 150.dp)
                .shadow(2.dp, shape = RoundedCornerShape(12.dp))
                .background(cardColorStatus, RoundedCornerShape(12.dp))
                .clip(
                    RoundedCornerShape(12.dp)
                )
                .padding(8.dp)
        )
        {
            Column {
                /*
                Header
                 */
                Row {
                    Text(text = "telda", fontSize = 12.sp)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "titanium", fontSize = 12.sp)

                }
                /**
                 * Drag drop Area
                 */
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {

                    /*
                    Editable Area
                     */
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f)
                            .border(1.dp, Color.Black)
                            .padding(8.dp)
                    ) {
                        children.forEach {
                            DraggableItem(it)
                        }
                    }
                    /*
                     un Editable Area
                     */
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(50.dp)
                    )
                    {

                    }
                }
                /*
                Bottom Area
                 */
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "MasterCard logo", fontSize = 12.sp)
                }

            }
        }

    }
}


@Composable
fun DraggableItem(type: ItemType) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
         scale = (scale * zoomChange)
         offsetX = (offsetX + panChange.x)
         offsetY = (offsetY+panChange.y)
    }
    Box(modifier = Modifier.size(120.dp)
        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
        .border(2.dp,Color.Black)
        .transformable(state).scale(scale)
    )
    {
        when (type) {
            is ItemType.Icon -> {
                Icon(
                    imageVector = type.vectorDrawable,
                    contentDescription = "",
                )
            }

            is ItemType.Text -> {
                Text(text = "${scale.roundToInt()}")
            }
        }

    }




}