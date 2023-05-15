package com.eritlab.jexmon.presentation.screens.checkout_screen.component

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.eritlab.jexmon.R
import com.eritlab.jexmon.domain.model.checkout.ItemDTOS
import com.eritlab.jexmon.domain.model.getCart.Item
import com.eritlab.jexmon.presentation.common.CustomDefaultBtn
import com.eritlab.jexmon.presentation.common.CustomTextField
import com.eritlab.jexmon.presentation.component.DefaultBackArrow
import com.eritlab.jexmon.presentation.component.ErrorSuggestion
import com.eritlab.jexmon.presentation.graphs.detail_graph.DetailScreen
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.TextColor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

//@Preview(showBackground = true)
@Composable

fun CheckOut(
    navController: NavController,
    viewModel: CheckoutViewModel = hiltViewModel()
) {
    val state by viewModel.checkoutResponse.collectAsState()
    val ctx = LocalContext.current
    val shareReference = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
    val editor = shareReference.edit()
    val jsonString = shareReference.getString("arrayCart",null)
    val gson = Gson()
    val type : Type = object :TypeToken<ArrayList<Item>>(){}.type
    val itemCarts:ArrayList<Item> = gson.fromJson(jsonString,type)
    if(itemCarts==null){
        Log.e("itemcart","oh no null")
    }
    else{
        Log.e("itemcart 2",itemCarts.toString())
    }
    val totalMoney = shareReference.getInt("total",0)
    val id = shareReference.getInt("id",0)
    Log.e("totalMoney",totalMoney.toString())
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var Name by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    val phoneNumberErrorMessage = remember { mutableStateOf("") }
    val NameErrorMessage = remember { mutableStateOf("") }
    val addressErrorMessage = remember { mutableStateOf("") }
    val NameErrorState = remember { mutableStateOf(false) }
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
                    navController.popBackStack()
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
                .constrainAs(input) {
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
            if (NameErrorState.value) {
                ErrorSuggestion(NameErrorMessage.value)
            }
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
            if (addressErrorState.value) {
                ErrorSuggestion(addressErrorMessage.value)
            }
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
            if (phoneNumberErrorState.value) {
                ErrorSuggestion(phoneNumberErrorMessage.value)
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(product) {
                    top.linkTo(input.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .wrapContentHeight()
        ){
            for(item in itemCarts){
                val image = rememberImagePainter(data = item.thumbnailPath)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = image,
                        contentDescription = null,
                        modifier = Modifier
                            .size(80.dp)
                            .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                            .padding(10.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Column() {
                        Text(
                            text = "${item.itemName}",
                            fontWeight = FontWeight(700),
                            fontSize = 16.sp,

                            )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row() {
                            Text(
                                text = "$${item.price}",
                                color = MaterialTheme.colors.PrimaryColor,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "  x${item.quantity}", color = MaterialTheme.colors.TextColor)
                        }
                    }
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(method) {
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
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(total) {
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
                    text = "$${totalMoney}",
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
                    val isName = Name.text.length>0
                    val isAddress = address.text.length>0
                    val isPhone = address.text.length>0
                    if(Name.text.isBlank()){
                        if(NameErrorState.value==false){
                            NameErrorState.value=true
                            NameErrorMessage.value = "Name must have input"
                        }
                    }
                    else{
                        NameErrorState.value=false
                    }
                    if(address.text.isBlank()){
                        if(addressErrorState.value==false){
                            addressErrorState.value=true
                            addressErrorMessage.value = "Address must have input"
                        }
                    }
                    else{
                        addressErrorState.value=false
                    }
                    if(phoneNumber.text.isBlank()){
                        if(phoneNumberErrorState.value==false){
                            phoneNumberErrorState.value=true
                            phoneNumberErrorMessage.value = "Phone must have input"
                        }
                    }
                    else{
                        phoneNumberErrorState.value=false
                    }
                    if(isAddress&&isName&&isPhone){

                        viewModel.checkout(id, Name.text,address.text,phoneNumber.text,itemCarts,selectedOption.toString(),totalMoney)
//                        Log.e("selected", selectedOption.toString())
                    }
                }
            }
            LaunchedEffect(state) {
                if(state!=null){
                    editor.putString("nameCheckout",Name.text)
                    editor.putString("addressCheckout",address.text)
                    editor.putString("phoneCheckout",phoneNumber.text)
                    editor.apply()
                    Log.e("Message", state!!.message.toString())
                    Toast.makeText(ctx,state!!.message,Toast.LENGTH_LONG).show()
                    navController.navigate(DetailScreen.DetailCheckOut.route)

                }
//                Log.d("RadioButtonExample", "Selected option: $selectedOption")
            }

        }
    }
}