package com.example.myapplication.compose.lazyCol

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SimpleLazyList(count : Int, modifier: Modifier) {

    val lists = ArrayList<Int>()
    for (i in 0..count) {
        lists.add(i)
    }

    LazyColumn(
        modifier = modifier.fillMaxWidth().fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
//            .pointerInput(key1 = ){
//                detectDragGesturesAfterLongPress(
//                    onDragStart = {},
//                    onDrag = { c, d -> }
//
//                )
//            }
    ) {

        stickyHeader {
            Header("TTTT")
        }

//        item {
//            Text(modifier = modifier.padding(10.dp)  , text =  "TTTT")
//        }
        items(lists) { i ->
            ItemLikeGoogleDrive(i)
        }

        stickyHeader {
            Header("IIII")
        }


        items(lists) { i ->
            ItemLikeGoogleDrive(i)
        }


        stickyHeader {
            Header("BBBB")
        }

        items(lists) { i ->
            ItemLikeGoogleDrive(i)
        }
    }

}

@Composable
fun ItemLikeGoogleDrive(id: Int, modifi: Modifier = Modifier) {
    Row(modifier = modifi
        .padding(5.dp)
        .clickable { }
        .background(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = MaterialTheme.shapes.medium
        ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {

        Image(
            alignment = Alignment.Center,
            painter = painterResource(R.drawable.ic_android_black_24dp),
            contentDescription = "Artist image",
            modifier = modifi.padding(10.dp),
            contentScale = ContentScale.Crop
        )

        Column(modifier = modifi
            .fillMaxWidth(),
        ) {

            Text(
                text = "this is an Android",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                color = MaterialTheme.colorScheme.outline,
                fontStyle = Italic,
                style = MaterialTheme.typography.bodySmall,
                text = "No. $id"
            )

        }

    }
}


@Composable
fun Header(name: String, modifi: Modifier = Modifier){

    Column(
        modifier = modifi
            .padding(start = 5.dp , end = 5.dp , top = 5.dp)
            .height(54.dp)
            .background(MaterialTheme.colorScheme.surfaceTint,
                shape = MaterialTheme.shapes.large)
            .fillMaxWidth()
        ,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.surface,
            text = name,
            modifier = modifi.padding(start = 5.dp),
            style = MaterialTheme.typography.titleLarge,
        )
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = "id:pixel_8_pro")
@Composable
fun Preview() {
    SimpleLazyList(10, Modifier)
}