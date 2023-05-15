package com.eritlab.jexmon.presentation.screens.dashboard_screen.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.eritlab.jexmon.R
import com.eritlab.jexmon.presentation.screens.dashboard_screen.PopularBookViewModel
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.TextColor

@Composable
fun DashboardScreen(
    popularProductState: LazyListState = rememberLazyListState(),
    suggestionProductState: LazyListState = rememberLazyListState(),
    productViewModel: PopularBookViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit,
) {
    val navHostController = rememberNavController()
    val state by productViewModel.popular.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .background(color = Color(0xFF4a3298), shape = RoundedCornerShape(10.dp))
                .padding(15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text("A Spring Surprise", color = Color.White)
            Text(
                "Cashback 25%",
                color = Color.White,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
//        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Special for you", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "See More", color = MaterialTheme.colors.TextColor)
        }

        Spacer(modifier = Modifier.height(15.dp))
//special offer cart
        LazyRow(
            state = popularProductState,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {
            item {
                ConstraintLayout(
                    modifier = Modifier
                        .width(280.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    //constrains
                    val (bannerText, bannerImage) = createRefs()
                    Image(
                        painter = painterResource(id = R.drawable.mentality),
                        contentDescription = "",
                        modifier = Modifier
                            .constrainAs(bannerImage) {}
                            .fillMaxHeight()
                            .fillMaxWidth()
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(Color(0x8DB3B0B0))
                            .padding(15.dp)
                            .constrainAs(bannerText) {
                                top.linkTo(bannerImage.top)
                                bottom.linkTo(bannerImage.bottom)
                                start.linkTo(bannerImage.start)
                                end.linkTo(bannerImage.end)
                                height = Dimension.fillToConstraints
                                width = Dimension.fillToConstraints
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "Mentality Books",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        )
                    }


                }
            }
            item {
                //second item
                ConstraintLayout(
                    modifier = Modifier
                        .width(280.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    //constrains
                    val (bannerText2, bannerImage2) = createRefs()
                    Image(
                        painter = painterResource(id = R.drawable.horror_book),
                        contentDescription = "",
                        modifier = Modifier
                            .constrainAs(bannerImage2) {}
                            .fillMaxHeight()
                            .fillMaxWidth()
                    )
                    Column(
                        modifier = Modifier
                            .background(Color(0x8DB3B0B0))
                            .padding(15.dp)
                            .constrainAs(bannerText2) {
                                top.linkTo(bannerImage2.top)
                                bottom.linkTo(bannerImage2.bottom)
                                start.linkTo(bannerImage2.start)
                                end.linkTo(bannerImage2.end)
                                height = Dimension.fillToConstraints
                                width = Dimension.fillToConstraints
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Horror Books",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
            item {
                //second item
                ConstraintLayout(
                    modifier = Modifier
                        .width(280.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    //constrains
                    val (bannerText2, bannerImage2) = createRefs()
                    Image(
                        painter = painterResource(id = R.drawable.harry),
                        contentDescription = "",
                        modifier = Modifier
                            .constrainAs(bannerImage2) {}
                            .fillMaxHeight()
                            .fillMaxWidth()
                    )
                    Column(
                        modifier = Modifier
                            .background(Color(0x8DB3B0B0))
                            .padding(15.dp)
                            .constrainAs(bannerText2) {
                                top.linkTo(bannerImage2.top)
                                bottom.linkTo(bannerImage2.bottom)
                                start.linkTo(bannerImage2.start)
                                end.linkTo(bannerImage2.end)
                                height = Dimension.fillToConstraints
                                width = Dimension.fillToConstraints
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Story Books",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
            item {
                //second item
                ConstraintLayout(
                    modifier = Modifier
                        .width(280.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(20.dp))
                ) {
                    //constrains
                    val (bannerText2, bannerImage2) = createRefs()
                    Image(
                        painter = painterResource(id = R.drawable.spirituality),
                        contentDescription = "",
                        modifier = Modifier
                            .constrainAs(bannerImage2) {}
                            .fillMaxHeight()
                            .fillMaxWidth()
                    )
                    Column(
                        modifier = Modifier
                            .background(Color(0x8DB3B0B0))
                            .padding(15.dp)
                            .constrainAs(bannerText2) {
                                top.linkTo(bannerImage2.top)
                                bottom.linkTo(bannerImage2.bottom)
                                start.linkTo(bannerImage2.start)
                                end.linkTo(bannerImage2.end)
                                height = Dimension.fillToConstraints
                                width = Dimension.fillToConstraints
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Spirituality Books",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }



        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Popular Book", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "See More", color = MaterialTheme.colors.TextColor)
        }

        Spacer(modifier = Modifier.height(8.dp))

        val isLoading = state.isEmpty()
        if(isLoading){
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
                    .fillMaxHeight(),
            )
        }
        //popular product
        LazyRow(
            state = suggestionProductState,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) {

            items(state!!.size) {
                val image = rememberImagePainter(data = state[it].imagePath)
                //favourite state rememberable
                Column {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                            .clip(RoundedCornerShape(10.dp))
                            .clickable {
                                onItemClick(state[it].id)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = image,
                            contentDescription = "Image"
                        )
                    }
                    Text(
                        text = state[it].productName,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.width(150.dp)
                    )


                    Row(
                        modifier = Modifier
                            .width(150.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "$ ${state[it].price}",
                            fontWeight = FontWeight(600),
                            color = MaterialTheme.colors.PrimaryColor
                        )
                    }

                }
            }
        }
    }
}