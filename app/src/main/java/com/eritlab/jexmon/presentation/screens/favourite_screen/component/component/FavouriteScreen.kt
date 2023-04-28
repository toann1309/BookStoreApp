package com.eritlab.jexmon.presentation.screens.favourite_screen.component

import android.annotation.SuppressLint
import android.util.Log
import android.widget.GridView
import android.widget.Toast
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
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.eritlab.jexmon.R
import com.eritlab.jexmon.presentation.common.component.CustomDialog
import com.eritlab.jexmon.presentation.dashboard_screen.component.NavigationBar
import com.eritlab.jexmon.presentation.graphs.detail_graph.DetailScreen
import com.eritlab.jexmon.presentation.graphs.home_graph.HomeNavGraph
import com.eritlab.jexmon.presentation.screens.dashboard_screen.DashboardViewModel
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.PrimaryLightColor


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FavouriteScreen(
    productViewModel: FavouriteViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit,
) {
//    val homeViewModel:FavouriteViewModel by hiltViewModel()
//    val games by productViewModel.games.collectAsState()
    var showDialogs by remember{
        mutableStateOf(false)
    }

    val state = productViewModel.games.value
//    Log.e("data", games.toString())
    Log.e("Data", state.toString())
    val numbers = (0..20).toList()
    val scrollState = rememberScrollState()
    val ctx = LocalContext.current
    Spacer(modifier = Modifier.height(15.dp))
    Column() {
        Button(onClick = {
            showDialogs = true
        }, modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, bottom = 10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.PrimaryColor,
                contentColor = Color.White
            ),) {
            Row() {
                Text(text = "Filter", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Icon(painter = painterResource(id = R.drawable.filter_svgrepo_com), contentDescription = "Filter Icon")
            }
        }
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
    if (showDialogs){
        CustomDialog(
            title = "Filter",
            onDismiss = { showDialogs=false },
            onPositiveButtonClicked = {
                                      showDialogs=false
                Toast.makeText(ctx,"Positive button clicked", Toast.LENGTH_LONG).show();
            },
            onNegativeButtonClicked = {
                showDialogs=false
                Toast.makeText(ctx,"Negative Button clicked", Toast.LENGTH_LONG).show();

            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        )
    }
}