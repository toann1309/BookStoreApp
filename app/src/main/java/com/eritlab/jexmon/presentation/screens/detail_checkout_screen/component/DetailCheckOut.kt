package com.eritlab.jexmon.presentation.screens.detail_checkout_screen.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.eritlab.jexmon.R
import com.eritlab.jexmon.presentation.common.CustomTextField
import com.eritlab.jexmon.presentation.common.component.DefaultBackArrow
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.PrimaryLightColor
import com.eritlab.jexmon.presentation.ui.theme.SecondaryColor
import com.eritlab.jexmon.presentation.ui.theme.TextColor

@Preview(showBackground = true)

@Composable

fun DetailsCheckOut () {
    var information by remember { mutableStateOf(TextFieldValue("")) }
    val infomationErrorState = remember { mutableStateOf(false) }

    ConstraintLayout(modifier = Modifier.fillMaxSize(1f)) {
        val (topBar, input, product, method, total) = createRefs()
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
                DefaultBackArrow {

                }
            }
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
                    text = "Nguyễn Minh Toàn (0948349445), " +
                            "175/113/11 đường số 2, phường Tăng Nhơn Phú B, quận 9, thành phố Hồ CHí Minh",
                    modifier = Modifier.padding(10.dp, 26.dp, 18.dp, 0.dp),
                    fontSize = 16.sp,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
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
                            text = "Wireless Controller for PS4™",
                            fontWeight = FontWeight(700),
                            fontSize = 16.sp,

                            )
                        Spacer(modifier = Modifier.height(8.dp))

                    }
                    Column() {
                        Text(
                            text = "$79.99",
                            color = MaterialTheme.colors.PrimaryColor,
                            fontSize = 16.sp,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Column() {
                        Text(
                            text = "  x1",
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
                            text = "High Quality Sport Shoes",
                            fontWeight = FontWeight(700),
                            fontSize = 16.sp,

                            )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Column() {
                        Text(
                            text = "$100.25",
                            color = MaterialTheme.colors.PrimaryColor,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Column() {

                        Text(text = "  x1",
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
                            text = "Nike Sport White - Man Pant",
                            fontWeight = FontWeight(700),
                            fontSize = 16.sp,

                            )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Column() {
                        Text(
                            text = "$49.99",
                            color = MaterialTheme.colors.PrimaryColor,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Column() {
                        Text(
                            text = "  x1",
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
                            text = "Gloves XC Omega - Polygon",
                            fontWeight = FontWeight(700),
                            fontSize = 16.sp,

                            )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Column() {
                        Text(
                            text = "$36.55",
                            color = MaterialTheme.colors.PrimaryColor,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Column() {
                        Text(
                            text = "  x1",
                            color = MaterialTheme.colors.TextColor,
                            fontSize = 16.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        .background(MaterialTheme.colors.SecondaryColor)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Back",
                        color = MaterialTheme.colors.PrimaryLightColor,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )

                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(60.dp)
                        .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        .background(MaterialTheme.colors.PrimaryColor),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Confirm",
                        color = MaterialTheme.colors.PrimaryLightColor,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }


    }
}