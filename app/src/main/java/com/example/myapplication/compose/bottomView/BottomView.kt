package com.example.myapplication.compose.bottomView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun BottomBar(itemStatus: Int, onItemChange: (Int) -> Unit) {

    Row(
        modifier = Modifier
            .height(66.dp)
            .background(Color.White)
    ) {
        IconButton(modifier = Modifier
            .fillMaxHeight()
            .weight(1f), onClick = { onItemChange(0) }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Icon(
                    tint = if (itemStatus == 0) Color.Red else Color.Black,
                    modifier = Modifier,
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Localized description"
                )
                if (itemStatus == 0)
                    Text(
                        modifier = Modifier, text = "Add"
                    )


            }
        }
        IconButton(modifier = Modifier
            .fillMaxHeight()
            .weight(1f), onClick = { onItemChange(1) }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    tint = if (itemStatus == 1) Color.Red else Color.Black,
                    imageVector = Icons.Filled.Call,
                    contentDescription = "Localized description"
                )

                if (itemStatus == 1) Text(text = "Call")

            }
        }
        IconButton(modifier = Modifier
            .fillMaxHeight()
            .weight(1f), onClick = { onItemChange(2) }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Icon(
                    tint = if (itemStatus == 2) Color.Red else Color.Black,
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Localized description"
                )
                if (itemStatus == 2)
                    Text(text = "LocationOn")
            }
        }
        IconButton(modifier = Modifier
            .fillMaxHeight()
            .weight(1f), onClick = { onItemChange(3) }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    tint = if (itemStatus == 3) Color.Red else Color.Black,
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Localized description"
                )
                if (itemStatus == 3)
                    Text(text = "Info")
            }
        }
    }
}