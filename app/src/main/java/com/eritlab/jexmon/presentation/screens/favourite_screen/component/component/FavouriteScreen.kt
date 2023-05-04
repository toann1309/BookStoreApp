package com.eritlab.jexmon.presentation.screens.favourite_screen.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.eritlab.jexmon.domain.item.BookItem
import com.eritlab.jexmon.domain.model.bookModel.BookModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FavouriteScreen(
    productViewModel: BookViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit,
) {
    val state by productViewModel.books.collectAsState()
    Log.e("Data", state.toString())
    val numbers = (0..20).toList()
    val scrollState = rememberScrollState()
    Spacer(modifier = Modifier.height(15.dp))
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
//        .wrapContentSize(unbounded = true),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        val isLoading = state.isEmpty()


        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
                    .fillMaxHeight(),
            )
        }
        for(item in state){
            ConstraintLayout() {
                BookCard(bookCard = item, onItemClick = onItemClick)
            }
        }





//        for(item in state.){
//            val image = rememberImagePainter(data = item.imagePath)
//            Row(modifier = Modifier
//                .fillMaxWidth()
//                .height(150.dp)
//                .background(color = Color(0xFFF0F0F0), shape = RoundedCornerShape(10.dp))
//                .padding(15.dp),
//                horizontalArrangement = Arrangement.spacedBy(15.dp)
//            ) {
//                Box(modifier = Modifier
//                    .size(120.dp)
//                    .clip(RoundedCornerShape(10.dp))
//                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
//                    .clickable {
//                        onItemClick(item.id)
//                    },
//                    contentAlignment = Alignment.Center
//                ){
//                    Image(painter = image, contentDescription = "Ehllo"  )
//
//                }
//                Column {
//                    Text(text = item.productName, fontSize = 18.sp, color = Color.Black)
//                    Text(text = "$${item.price}", fontSize = 14.sp, color = Color(0xFFFF7643))
//                    Text(text = item.productName, fontSize = 10.sp, maxLines = 4, overflow = TextOverflow.Ellipsis)
//                }
//            }
//        }
    }
}
@Composable
fun BookCard(bookCard:BookItem, onItemClick: (Int) -> Unit){
    val image = rememberImagePainter(data = bookCard.imagePath)
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
                onItemClick(bookCard.id)
            },
            contentAlignment = Alignment.Center
        ){
            Image(painter = image, contentDescription = "Ehllo"  )

        }
        Column {
            Text(text = bookCard.productName, fontSize = 18.sp, color = Color.Black)
            Text(text = "$${bookCard.price}", fontSize = 14.sp, color = Color(0xFFFF7643))
            Text(text = bookCard.productName, fontSize = 10.sp, maxLines = 4, overflow = TextOverflow.Ellipsis)
        }
    }
}