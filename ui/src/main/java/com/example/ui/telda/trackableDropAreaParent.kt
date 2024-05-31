package com.example.ui.telda

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showBackground = true, name = "DropArea")
@Composable
fun DropArea(modifier: Modifier = Modifier, cardColor:Color = Color.LightGray)

   {

    val items = listOf("Item 1", "Item 2", "Item 3")
    val cardColorStatus by animateColorAsState(cardColor, animationSpec = tween(1000) ,label = "Card Color")
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
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)) {}
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