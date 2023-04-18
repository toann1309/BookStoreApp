package com.eritlab.jexmon.presentation.screens.favourite_screen.component

import android.util.Log
import android.widget.GridView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.eritlab.jexmon.R
import com.eritlab.jexmon.presentation.dashboard_screen.component.NavigationBar
import com.eritlab.jexmon.presentation.graphs.detail_graph.DetailScreen
import com.eritlab.jexmon.presentation.graphs.home_graph.HomeNavGraph
import com.eritlab.jexmon.presentation.screens.dashboard_screen.DashboardViewModel
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.PrimaryLightColor


@Composable
fun FavouriteScreen(
    popularProductState: LazyListState = rememberLazyListState(),
    suggestionProductState: LazyListState = rememberLazyListState(),
    productViewModel: DashboardViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit
) {
    val state = productViewModel.state.value
    val numbers = (0..20).toList()
    val scrollState = rememberScrollState()
    Spacer(modifier = Modifier.height(15.dp))
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        for(item in state.product!!){
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
                    Image(painter = painterResource(item.images[0]), contentDescription = "Ehllo"  )

                }
                Column {
                    Text(text = item.title, fontSize = 18.sp, color = Color.Black)
                    Text(text = "$${item.price}", fontSize = 14.sp, color = Color(0xFFFF7643))
                    Text(text = item.description, fontSize = 10.sp, maxLines = 4, overflow = TextOverflow.Ellipsis)
                }
            }
        }
    }
}