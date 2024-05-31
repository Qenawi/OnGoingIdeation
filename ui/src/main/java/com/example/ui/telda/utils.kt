package com.example.ui.telda

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.dp

fun isInParent(droppedPosition: Offset, parentBounds: Rect): Boolean {
    return droppedPosition.x >= parentBounds.left &&
            droppedPosition.x <= parentBounds.right &&
            droppedPosition.y >= parentBounds.top &&
            droppedPosition.y <= parentBounds.bottom
}
fun isOverlapping(droppedPosition: Offset, list: List<Offset>, itemSize: Float): Boolean {
    val droppedRect = Rect(
        left = droppedPosition.x,
        top = droppedPosition.y,
        right = droppedPosition.x + itemSize,
        bottom = droppedPosition.y + itemSize
    )

    return list.any { offset ->
        val existingRect = Rect(
            left = offset.x,
            top = offset.y,
            right = offset.x + itemSize,
            bottom = offset.y + itemSize
        )
        droppedRect.overlapsOO(existingRect)
    }
}

fun Rect.overlapsOO(other: Rect): Boolean {
    return this.left < other.right &&
            this.right > other.left &&
            this.top < other.bottom &&
            this.bottom > other.top
}


