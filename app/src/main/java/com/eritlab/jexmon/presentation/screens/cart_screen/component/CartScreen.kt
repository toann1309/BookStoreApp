package com.eritlab.jexmon.presentation.screens.cart_screen.component

import android.service.autofill.OnClickAction
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.eritlab.jexmon.presentation.ui.theme.TextColor
import com.eritlab.jexmon.R
import com.eritlab.jexmon.presentation.common.CustomDefaultBtn
import com.eritlab.jexmon.presentation.graphs.detail_graph.DetailScreen
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.PrimaryLightColor


@Composable
fun CartScreen(
    navController: NavController,
    popBack: () -> Unit,

) {
//    val state = viewModel.state.value
    val context = LocalContext.current
    var itemDrag by remember { mutableStateOf(0f) }
    var quantity by remember { mutableStateOf(1) }
//    var products by remember { mutableStateOf(listOf<>())}


    ConstraintLayout(modifier = Modifier.fillMaxSize(1f)) {
        val (topBar, product, checkout) = createRefs()

        Row(
            modifier = Modifier
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth()
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(0.5f)) {
                IconButton(
                    onClick = {
                        popBack()
                    },
                    modifier = Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .clip(CircleShape)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.back_icon),
                        contentDescription = null
                    )
                }
            }
            Box(modifier = Modifier.weight(0.7f)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Your Cart",
                        color = MaterialTheme.colors.TextColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "4 items",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.TextColor,
                    )

                }
            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(product) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .wrapContentHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ps4_console_white_1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Column() {
                    Text(
                        text = "Wireless Controller for PS4™",
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,

                        )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row() {
                        Text(
                            text = "$79.99",
                            color = MaterialTheme.colors.PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(10.dp, 4.dp, 16.dp, 0.dp)

                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    if (quantity > 1) {
                                        quantity--
                                    }
                                },
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.PrimaryColor,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .width(30.dp)
                                    .height(30.dp)


                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.remove),
                                    contentDescription = null,
                                )
                            }
                            Text(
                                text = quantity.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .width(35.dp)
                                    .wrapContentHeight()
                            )
                            IconButton(
                                onClick = {
                                    if (quantity < 5) {
                                        quantity++
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "You can add maximum 5 item at a time.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.PrimaryColor,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .width(30.dp)
                                    .height(30.dp)

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.plus_icon),
                                    contentDescription = null,

                                )
                            }

                            IconButton(
                                onClick = {

                                },
                                modifier = Modifier
                                    .padding(40.dp, 0.dp, 0.dp, 0.dp)
                                    .width(30.dp)
                                    .height(30.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.trash),
                                    contentDescription = null
                                )
                            }
                        }
//                        Text(text = "  x1", color = MaterialTheme.colors.TextColor)
                    }
                }
            }







            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.shoes2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Column() {
                    Text(
                        text = "High Quality Sport Shoes",
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,

                        )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row() {
                        Text(
                            text = "$100.25",
                            color = MaterialTheme.colors.PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(10.dp, 4.dp, 16.dp, 0.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    if (quantity > 1) {
                                        quantity--
                                    }
                                },
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.PrimaryColor,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .width(30.dp)
                                    .height(30.dp)

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.remove),
                                    contentDescription = null
                                )
                            }
                            Text(
                                text = quantity.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .width(35.dp)
                                    .wrapContentHeight()
                            )
                            IconButton(
                                onClick = {
                                    if (quantity < 5) {
                                        quantity++
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "You can add maximum 5 item at a time.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.PrimaryColor,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .width(30.dp)
                                    .height(30.dp)

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.plus_icon),
                                    contentDescription = null
                                )
                            }

                            IconButton(
                                onClick = {

                                },
                                modifier = Modifier
                                    .padding(40.dp, 0.dp, 0.dp, 0.dp)
                                    .width(30.dp)
                                    .height(30.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.trash),
                                    contentDescription = null
                                )
                            }
                        }
//                        Text(text = "  x1", color = MaterialTheme.colors.TextColor)
                    }
                }
            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_popular_product_2),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Column() {
                    Text(
                        text = "Nike Sport White - Man Pant",
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,

                        )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row() {
                        Text(
                            text = "$49.99",
                            color = MaterialTheme.colors.PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(10.dp, 4.dp, 16.dp, 0.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    if (quantity > 1) {
                                        quantity--
                                    }
                                },
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.PrimaryColor,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .width(30.dp)
                                    .height(30.dp)

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.remove),
                                    contentDescription = null
                                )
                            }
                            Text(
                                text = quantity.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .width(35.dp)
                                    .wrapContentHeight()
                            )
                            IconButton(
                                onClick = {
                                    if (quantity < 5) {
                                        quantity++
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "You can add maximum 5 item at a time.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.PrimaryColor,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .width(30.dp)
                                    .height(30.dp)

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.plus_icon),
                                    contentDescription = null
                                )
                            }

                            IconButton(
                                onClick = {

                                },
                                modifier = Modifier
                                    .padding(40.dp, 0.dp, 0.dp, 0.dp)
                                    .width(30.dp)
                                    .height(30.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.trash),
                                    contentDescription = null
                                )
                            }
                        }
//                        Text(text = "  x1", color = MaterialTheme.colors.TextColor)
                    }
                }
            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.glove),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Column() {
                    Text(
                        text = "Gloves XC Omega - Polygon",
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,

                        )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row() {
                        Text(
                            text = "$36.55",
                            color = MaterialTheme.colors.PrimaryColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(10.dp, 4.dp, 16.dp, 0.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = {
                                    if (quantity > 1) {
                                        quantity--
                                    }
                                },
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.PrimaryColor,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .width(30.dp)
                                    .height(30.dp)

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.remove),
                                    contentDescription = null
                                )
                            }
                            Text(
                                text = quantity.toString(),
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .width(35.dp)
                                    .wrapContentHeight()
                            )
                            IconButton(
                                onClick = {
                                    if (quantity < 5) {
                                        quantity++
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "You can add maximum 5 item at a time.",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                },
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colors.PrimaryColor,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                                    .width(30.dp)
                                    .height(30.dp)

                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.plus_icon),
                                    contentDescription = null
                                )
                            }
                            
                            IconButton(
                                onClick = {

                                },
                                modifier = Modifier
                                    .padding(40.dp, 0.dp, 0.dp, 0.dp)
                                    .width(30.dp)
                                    .height(30.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.trash),
                                    contentDescription = null
                                )
                            }
                        }
//                        Text(text = "  x1", color = MaterialTheme.colors.TextColor)
                    }
                }
            }


        }




        Column(
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(checkout) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(product.bottom)
                }
                .background(
                    color = MaterialTheme.colors.PrimaryLightColor,
                    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                )
                .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.receipt),
                    contentDescription = null,
                    tint = MaterialTheme.colors.PrimaryColor,
                    modifier = Modifier
                        .size(45.dp)
                        .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(15.dp))
                        .padding(10.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Text("Add vouture Code")
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_right),
                        contentDescription = null,
                    )
                }
            }
            //btn
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Text(text = "Total")
                    Text(
                        text = "$266.78",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.PrimaryColor
                    )

                }
                Box(
                    modifier = Modifier
                        .width(150.dp)
                ) {
                    CustomDefaultBtn(shapeSize = 15f, btnText = "Check Out",
                        onClick = {
                            navController.navigate(DetailScreen.CheckOut.route)
                        }
                    )
                }

            }


        }

    }
}