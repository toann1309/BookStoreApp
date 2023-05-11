package com.eritlab.jexmon.presentation.screens.checkout_screen.component

import android.location.Address
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
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
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.Navigator
import com.eritlab.jexmon.R
import com.eritlab.jexmon.presentation.common.CustomDefaultBtn
import com.eritlab.jexmon.presentation.common.CustomTextField
import com.eritlab.jexmon.presentation.screens.checkout_screen.component.component.DefaultBackArrow
import com.eritlab.jexmon.presentation.graphs.detail_graph.DetailScreen
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.TextColor
import kotlinx.coroutines.NonDisposableHandle.parent

//@Preview(showBackground = true)
@Composable

fun CheckOut(
    navController: NavController,
    popBack: () -> Unit,
) {
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var Name by remember { mutableStateOf(TextFieldValue("")) }
    val NameErrorState = remember { mutableStateOf(false) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    val addressErrorState = remember { mutableStateOf(false) }
    val phoneNumberErrorState = remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("cash") }

    var itemDrag by remember { mutableStateOf(0f) }

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
                        text = "Checkout",
                        color = MaterialTheme.colors.TextColor,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(input){
                    top.linkTo(topBar.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                placeholder = "Nguyễn Văn A",
                trailingIcon = R.drawable.user,
                label = "Name",
                errorState = NameErrorState,
                keyboardType = KeyboardType.Text,
                visualTransformation = VisualTransformation.None,
                onChanged = { newText ->
                    Name = newText
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                placeholder = "Ho Chi Minh City",
                trailingIcon = R.drawable.location_point,
                label = "Address",
                errorState = addressErrorState,
                keyboardType = KeyboardType.Text,
                visualTransformation = VisualTransformation.None,
                onChanged = { newText ->
                    address = newText
                }
            )
            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                placeholder = "099999999",
                trailingIcon = R.drawable.phone,
                label = "Phone",
                errorState = phoneNumberErrorState,
                keyboardType = KeyboardType.Text,
                visualTransformation = VisualTransformation.None,
                onChanged = { newText ->
                    phoneNumber = newText
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(product){
                    top.linkTo(input.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .wrapContentHeight()
        ){
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
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "  x1", color = MaterialTheme.colors.TextColor)
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
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "  x1", color = MaterialTheme.colors.TextColor)
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
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "  x1", color = MaterialTheme.colors.TextColor)
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
                            fontWeight = FontWeight.Bold
                        )
                        Text(text = "  x1", color = MaterialTheme.colors.TextColor)
                    }
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(method){
                    top.linkTo(product.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
            ) {
                Text(
                    text = "Phương thức thanh toán",
                    fontSize = 18.sp,
                )

                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Ví điện tử",
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Thanh toán khi nhận hàng",
                    fontSize = 18.sp,
                )

            }


            Column(
                modifier = Modifier
                    .padding(16.dp),
            ) {

                RadioButton(
                    selected = selectedOption == "cash",
                    onClick = { selectedOption = "cash" },
                    modifier = Modifier.padding(16.dp, 64.dp, 10.dp, 0.dp)

                )
                RadioButton(
                    selected = selectedOption == "card",
                    onClick = { selectedOption = "card" },
                    modifier = Modifier.padding(16.dp, 0.dp, 10.dp, 16.dp)
                )
            }

        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .constrainAs(total){
                    top.linkTo(method.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            ) {
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
                CustomDefaultBtn(shapeSize = 15f, btnText = "Oder") {
                    navController.navigate(DetailScreen.DetailCheckOut.route)
                }
            }
            LaunchedEffect(selectedOption) {
                Log.d("RadioButtonExample", "Selected option: $selectedOption")
            }

        }
    }
}