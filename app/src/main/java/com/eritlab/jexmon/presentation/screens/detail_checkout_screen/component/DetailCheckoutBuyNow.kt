package com.eritlab.jexmon.presentation.screens.detail_checkout_screen.component

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.eritlab.jexmon.R
import com.eritlab.jexmon.domain.model.getCart.Item
import com.eritlab.jexmon.presentation.common.CustomDefaultBtn
import com.eritlab.jexmon.presentation.graphs.home_graph.ShopHomeScreen
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.TextColor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Composable
fun DetailCheckoutBuyNow(
    navController: NavController
){
    val ctx = LocalContext.current
    val shareReference = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
    val name = shareReference.getString("nameCheckout","")
    val address = shareReference.getString("addressCheckout","")
    val phone = shareReference.getString("phoneCheckout","")
    val buynowItem = shareReference.getString("buynowItem","")
    val buynowQuantity = shareReference.getInt("buynowQuantity",0)
    val buynowPrice = shareReference.getInt("buynowPrice",0)
    val buynowImage = shareReference.getString("buynowImage","")
    val buynowId = shareReference.getInt("buynowId",0)

    var information by remember { mutableStateOf(TextFieldValue("")) }
    val infomationErrorState = remember { mutableStateOf(false) }

    ConstraintLayout(modifier = Modifier.fillMaxSize(1f)) {
        val (topBar, inFo, product, btn) = createRefs()
        Row(
            modifier = Modifier
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
                .fillMaxWidth()
                .fillMaxSize()
                .constrainAs(topBar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(0.7f)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Order Details",
                        color = MaterialTheme.colors.TextColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }




        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp, 30.dp, 18.dp, 18.dp)
                .constrainAs(inFo) {
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },

            ) {
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.Gray, shape = RoundedCornerShape(6.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.location_point),
                    contentDescription = null,
                    modifier = Modifier.padding(10.dp, 6.dp, 18.dp, 0.dp)
                )
                Text(
                    text = "Địa chỉ nhận hàng",
                    modifier = Modifier.padding(30.dp, 6.dp, 0.dp, 0.dp),
                    fontWeight = FontWeight.Bold
                )

                Text (
                    text = "${name} (${phone}), " +
                            "${address}",
                    modifier = Modifier.padding(10.dp, 26.dp, 18.dp, 0.dp),
                    fontSize = 16.sp,
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp, 30.dp, 18.dp, 18.dp)
                .wrapContentHeight()
                .constrainAs(product) {
                    top.linkTo(inFo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        ){

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Column() {
                    Text(
                        text = "${buynowItem}",
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,

                        )
                    Spacer(modifier = Modifier.height(8.dp))

                }
                Column() {
                    Text(
                        text = "$${buynowPrice}",
                        color = MaterialTheme.colors.PrimaryColor,
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Column() {
                    Text(
                        text = "  x${buynowQuantity}",
                        color = MaterialTheme.colors.TextColor,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Column() {
                    Text(
                        text = "Total:",
                        fontWeight = FontWeight(700),
                        fontSize = 18.sp,

                        )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Column() {
                    Text(
                        text = "$${buynowPrice*buynowQuantity}",
                        color = MaterialTheme.colors.PrimaryColor,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp, 20.dp, 18.dp, 18.dp)
                .constrainAs(btn) {
                    top.linkTo(product.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CustomDefaultBtn(shapeSize = 15f, btnText = "Back to Home",) {
                    navController.navigate(ShopHomeScreen.DashboardScreen.route)
                }
            }
        }

    }
}