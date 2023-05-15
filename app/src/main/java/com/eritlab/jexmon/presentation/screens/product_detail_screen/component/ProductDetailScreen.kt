package com.eritlab.jexmon.presentation.screens.product_detail_screen.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.eritlab.jexmon.R
import com.eritlab.jexmon.presentation.screens.product_detail_screen.AddCartViewModel
import com.eritlab.jexmon.presentation.screens.product_detail_screen.BookDetailViewModel
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.TextColor

@JvmOverloads
@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@Composable
fun ProductDetailScreen(
    viewModel: BookDetailViewModel = hiltViewModel(),
    addCartViewModel:AddCartViewModel = hiltViewModel(),
    popBack: () -> Unit
) {
    val state by viewModel.bookDetail.collectAsState()
    val stateAddCart by addCartViewModel.addCartResponse.collectAsState()
    val context = LocalContext.current
    val shareReference = context.getSharedPreferences("data", Context.MODE_PRIVATE)
    val id = shareReference.getInt("id",0)
    if (state == null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center)
                    .fillMaxHeight(),
            )
        }
    }
    if (state != null) {
//        var colorSelected by remember { mutableStateOf(product.colors[product.colors.size - 1]) }
        var image by remember { mutableStateOf(state!!.imagesProduct[0]) }
        var quantity by remember { mutableStateOf(1) }



        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0x8DB3B0B0)),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
            //image
            Image(
                painter = rememberImagePainter(data = image),
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                for (item in state!!.imagesProduct) {
                    IconButton(
                        onClick = {
                            image = item
                        },
                        modifier = Modifier
                            .size(50.dp)
                            .border(
                                width = 1.dp,
                                color = if (image == item) MaterialTheme.colors.PrimaryColor else Color.Transparent,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .background(Color.White, shape = RoundedCornerShape(10.dp))
                            .padding(5.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        Image(
                            painter = rememberImagePainter(data = item),
                            contentDescription = null,
                        )

                    }
                }
//                items(product.screenshots.size) {
//
//                }

            }
            Spacer(modifier = Modifier.height(50.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                    )
                    .padding(15.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(15.dp)
                    ) {
                        Text(
                            text = state!!.productName,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(25.dp))
                        Text(
                            text = "$${state!!.price}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color(0xFFFF6436)
                        )

                        Spacer(modifier = Modifier.height(25.dp))
                        Text(
                            text = state!!.description,
                            fontSize = 16.sp,
                            color = MaterialTheme.colors.TextColor,

                            )
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(0x8DB3B0B0),
                            shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                        )
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
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
                                .background(color = Color.White, shape = CircleShape)
                                .clip(CircleShape)

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
                                .background(color = Color.White, shape = CircleShape)
                                .clip(CircleShape)

                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.plus_icon),
                                contentDescription = null
                            )
                        }
                    }
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(
                            Color.White,
                            shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
                        )
                        .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.PrimaryColor,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .width(150.dp)
                            .padding(top = 30.dp, bottom = 30.dp)
                            .height(60.dp)
                            .clip(RoundedCornerShape(15.dp)),
                        onClick = {
                              addCartViewModel.addCarts(state!!.id,id,quantity)
                        },
                    ) {
                        Text(text = "Add to Cart", fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.width(1.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.PrimaryColor,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .width(150.dp)
                            .padding(top = 30.dp, bottom = 30.dp)
                            .height(60.dp)
                            .clip(RoundedCornerShape(15.dp)),
                        onClick = {
                            Toast.makeText(
                                context,
                                "Go to checkout",
                                Toast.LENGTH_SHORT
                            ).show()

                        },
                    ) {
                        Text(text = "Buy now", fontSize = 16.sp)
                    }
                }
            }
            LaunchedEffect(stateAddCart){
                if(stateAddCart!=null){
                    Log.e("add cart",stateAddCart!!.message.toString())
                    Toast.makeText(context,stateAddCart!!.message.toString(),Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(context,"Chờ một chút !!!",Toast.LENGTH_LONG).show()
                }
            }
        }

    } else {
        Log.e("Tag", "Null rồi cái lol má")
    }
}
