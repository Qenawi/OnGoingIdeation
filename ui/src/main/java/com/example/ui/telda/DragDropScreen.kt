package com.example.ui.telda

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true, name = "CardEditScreen", showSystemUi = false)
@Composable
fun CardEditScreen() {
    val ctx = LocalContext.current
    var cardColor = remember { mutableStateOf(Color.LightGray) }
    MaterialTheme(colorScheme = MaterialTheme.colorScheme.copy(primary = Color.Black))
    {
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
                DropArea(modifier = Modifier.padding(start = 8.dp, top = 20.dp, bottom = 20.dp), cardColor = cardColor.value)
                Text(text = "Customize Card Screen", modifier = Modifier.padding(start = 12.dp))
                ColorSelection(
                    modifier = Modifier.padding(start = 12.dp, top = 12.dp),
                    onColorSelected = {
                        cardColor.value = it
                    })
            }
            /**
             * Save And Reset Area
             */
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            )
            {
                Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(12.dp)) {
                    Text(text = "Save")
                }
                Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(12.dp)) {
                    Text(text = "Reset")
                }
            }

        }
    }


}

@Composable
fun ColorSelection(modifier: Modifier = Modifier, onColorSelected: (Color) -> Unit) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(10.dp))
    {
        for (i in listOf(Color.LightGray,Color.Red, Color.Green, Color.Blue, Color.Yellow)) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .shadow(2.dp, shape = CircleShape)
                    .background(i, CircleShape)
                    .clickable {
                        onColorSelected(i)
                    })
        }
    }
}

