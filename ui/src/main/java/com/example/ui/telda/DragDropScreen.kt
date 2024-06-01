package com.example.ui.telda

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class ItemType {
    class Icon(val vectorDrawable: ImageVector) : ItemType()
    class Text : ItemType()

}

@Preview(showBackground = true, name = "CardEditScreen", showSystemUi = false)
@Composable
fun CardEditScreen() {
    val ctx = LocalContext.current
    val cardColor = remember { mutableStateOf(Color.LightGray) }
    val children = remember { mutableStateOf(listOf<ItemType>()) }
    MaterialTheme(colorScheme = MaterialTheme.colorScheme.copy(primary = Color.Black)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(text = "Customize Card Screen", modifier = Modifier.padding(start = 12.dp))
                DropArea(
                    modifier = Modifier.padding(start = 8.dp, top = 20.dp, bottom = 20.dp),
                    cardColor = cardColor.value,
                    children = children.value
                )
                Text(
                    text = "Customize Card Screen",
                    modifier = Modifier.padding(start = 12.dp, bottom = 12.dp, top = 12.dp)
                )
                ColorSelection(modifier = Modifier.padding(start = 12.dp), onColorSelected = {
                    cardColor.value = it
                })
                Text(
                    text = "Add Element",
                    modifier = Modifier.padding(start = 12.dp, bottom = 12.dp, top = 24.dp)
                )
                AddElement(modifier = Modifier.padding(start = 12.dp), addItemType = {
                    children.value += it
                })

            }
            /**
             * Save And Reset Area
             */
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(12.dp)) {
                    Text(text = "Save")
                }
                Button(
                    onClick = { children.value = emptyList() }, shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Reset")
                }
            }

        }
    }


}

/**
 * [AddElement]
 */
@Composable
fun AddElement(modifier: Modifier = Modifier, addItemType: (ItemType) -> Unit) {
    val expanded = remember { MutableTransitionState(false) }
    Column(modifier = modifier) {
        OutlinedButton(
            onClick = {
                addItemType.invoke(ItemType.Text())

            }, shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Add Text")
        }

        OutlinedButton(
            onClick = {
                expanded.targetState = true
            }, shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Add Icon")
        }
        AnimatedVisibility(visibleState = expanded) {
            Row {
                for (i in listOf(Icons.Default.Add, Icons.Default.Refresh, Icons.Default.Call)) {
                    IconButton(onClick = {
                        expanded.targetState = false
                        addItemType.invoke(ItemType.Icon(i))
                    }) {
                        Icon(imageVector = i, contentDescription = "")
                    }

                }
            }
        }
    }

}

/**
 * [ColorSelection]
 */
@Composable
fun ColorSelection(modifier: Modifier = Modifier, onColorSelected: (Color) -> Unit) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        for (i in listOf(Color.LightGray, Color.Red, Color.Green, Color.Blue, Color.Yellow)) {
            Box(modifier = Modifier
                .size(30.dp)
                .shadow(2.dp, shape = CircleShape)
                .background(i, CircleShape)
                .clickable {
                    onColorSelected(i)
                })
        }
    }
}


@Preview
@Composable
fun TrackPad() {
    var scale by remember { mutableFloatStateOf(1f) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var offsetY by remember { mutableFloatStateOf(0f) }
    val state = rememberTransformableState { zoomChange, panChange, rotationChange ->
        scale = (scale * zoomChange).coerceIn(0.2f, 3f)
        offsetX = (offsetX + panChange.x)
        offsetY = (offsetY + panChange.y)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "TrackPad",
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    Color.LightGray, RoundedCornerShape(12.dp)
                )
                .transformable(state)
        ) {
            Surface(modifier = Modifier
                .size(120.dp, 60.dp)
                .scale(scale)
                .offset { IntOffset(offsetX.toInt(), offsetY.toInt()) },
                shape = RoundedCornerShape(12.dp),
                content = {})
        }

    }
}