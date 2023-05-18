package com.eritlab.jexmon.presentation.screens.cart_screen.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.eritlab.jexmon.presentation.ui.theme.TextColor
import com.eritlab.jexmon.R
import com.eritlab.jexmon.domain.model.getCart.Item
import com.eritlab.jexmon.presentation.common.CustomDefaultBtn
import com.eritlab.jexmon.presentation.graphs.detail_graph.DetailScreen
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.PrimaryLightColor
import com.google.gson.Gson


@JvmOverloads
@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@Composable
fun CartScreen(
    navController: NavController,
    viewModel: GetCartViewModel= hiltViewModel(),
    deleteViewModel: DeleteCartViewModel = hiltViewModel()
) {
//    val state = viewModel.state.value
    var deleteKey by remember { mutableStateOf(0) }
    val ctx = LocalContext.current
    val shareReference = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
    val editor = shareReference.edit()
    val id = shareReference.getInt("id",1)
    var sum by remember { mutableStateOf(0) }
    var idDelete by remember {
        mutableStateOf(0)
    }
    viewModel.getCart(id)
    val state by viewModel.getCartResponse.collectAsState()
    val deleteState by deleteViewModel.deleteCartResponse.collectAsState()
    LaunchedEffect(state){
        if(state!=null){
            Log.e("name", state!!.userId.toString())
            for (item in state?.itemList!!){

                sum = (sum + item.quantity*item.price).toInt()

//                Log.e("${item.itemName}","${item.quantity}")
//                Log.e("sum", sum.toString())
            }
        }
    }
    LaunchedEffect(deleteState,deleteKey){
        Log.e("has",deleteState.toString())
        if(deleteState!=null){
            Log.e("message Delete",deleteState!!.message.toString())
            Toast.makeText(ctx,deleteState!!.message,Toast.LENGTH_SHORT).show()
        }
    }
    var itemDrag by remember { mutableStateOf(0f) }

    var total by remember {
        mutableStateOf(0)
    }
//    var products by remember { mutableStateOf(listOf<>())}


    ConstraintLayout(modifier = Modifier.fillMaxSize(1f).verticalScroll(rememberScrollState())) {
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
                        navController.popBackStack()
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
                    if(state!=null){
                        Text(
                            text = "${state?.itemList?.size} items",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.TextColor,
                        )
                    }
                    else{
                        Text(
                            text = "0 items",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.TextColor,
                        )
                    }

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
            if(state?.itemList==null){

            }
            else if(state==null){
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center)
                        .fillMaxHeight(),
                )
            }
            else{
                for(item in state?.itemList!!){
                    var quantity by remember { mutableStateOf(item.quantity) }
                    Log.e("quantity", quantity.toString())


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

                                                item.quantity = quantity - 1
                                                sum = (sum - item.price).toInt()
                                                quantity = quantity - 1
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

                                            item.quantity = quantity + 1
                                            sum = (sum + item.price).toInt()
                                            quantity = quantity + 1
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
                                            deleteKey++
                                            sum = 0
                                            idDelete = item.id
                                            Toast.makeText(ctx,"Đang xóa ${item.itemName}", Toast.LENGTH_SHORT).show()
                                            deleteViewModel.deleteCart(item.id)
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
            }
        }

        Log.e("sum1", sum.toString())
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                        text = "$${sum}",
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
//
                            if(state!!.itemList.size==0){
                                Toast.makeText(ctx,"Không có sản phẩm",Toast.LENGTH_SHORT).show()
                            }
                            else{
                                val gson = Gson()
                                val listString = gson.toJson(state!!.itemList)
                                editor.putString("arrayCart", listString)
                                editor.putInt("total",sum)
                                editor.apply()
                                Log.e("sum2", sum.toString())
                                Log.e("array",state!!.itemList.toString())
                                navController.navigate(DetailScreen.CheckOut.route)
                            }
                        }
                    )
                }

            }
        }
    }
}