package com.eritlab.jexmon.presentation.screens.filter_screen.component.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.eritlab.jexmon.presentation.screens.filter_screen.component.FilterGameViewModel
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FilterScreen(
    gameFilerModel:FilterGameViewModel = hiltViewModel(),
    onItemClick: (Int)->Unit,
){
    val state = gameFilerModel.gameFilter.value
    Log.e("Data filter", state.toString())
    Log.e("Data filter length", state.size.toString())

    val numbers = (0..20).toList()
    val scrollState = rememberScrollState()
    Spacer(modifier = Modifier.height(15.dp))
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        for(item in state){
            val image = rememberImagePainter(data = item.thumbnail)
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = Color(0xFFF0F0F0), shape = RoundedCornerShape(10.dp))
                .padding(15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Box(modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                    .clickable {
                        onItemClick(item.id)
                    },
                    contentAlignment = Alignment.Center
                ){
                    Image(painter = image, contentDescription = "Ehllo"  )

                }
                Column {
                    Text(text = item.title, fontSize = 18.sp, color = Color.Black)
                    Text(text = "$${item.id}", fontSize = 14.sp, color = Color(0xFFFF7643))
                    Text(text = item.short_description, fontSize = 10.sp, maxLines = 4, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}