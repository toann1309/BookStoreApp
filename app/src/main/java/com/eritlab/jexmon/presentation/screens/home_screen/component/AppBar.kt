package com.eritlab.jexmon.presentation.dashboard_screen.component

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eritlab.jexmon.R
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.presentation.graphs.home_graph.ShopHomeScreen
import com.eritlab.jexmon.presentation.graphs.option_graph.OptionScreen
import com.eritlab.jexmon.presentation.screens.cart_screen.component.GetCartViewModel
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.PrimaryLightColor

@Composable
fun AppBar(
    navController: NavController,
    isVisible: Boolean,
    searchCharSequence: (String) -> Unit,
    onCartIconClick: () -> Unit,
    onSearchClick:(String) -> Unit,
    viewModel: GetCartViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    val shareReference = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
    val id = shareReference.getInt("id",1)
    viewModel.getCart(id)
    val state by viewModel.getCartResponse.collectAsState()
    var typedText by remember {
        mutableStateOf("")
    }
    if (isVisible) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 30.dp, bottom = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = typedText,
                onValueChange = { newText ->
                    typedText = newText
                    searchCharSequence(newText)
                },
                singleLine = true,
                placeholder = { Text(text = "Search product") },
                leadingIcon = {
                    IconButton(onClick = {
                        Log.e("Hello",typedText)
                        onSearchClick(typedText)

                    }) {
                        Icon(painter = painterResource(id = R.drawable.search_icon), contentDescription = "Search Button")
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = MaterialTheme.colors.PrimaryColor
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClick(typedText)
                    }
                ),

                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.PrimaryLightColor,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .weight(1f),

                )
            ConstraintLayout {
                val (notification, notificationCounter) = createRefs()
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.PrimaryLightColor)
                        .constrainAs(notification) {}
                        .clickable {
                            onCartIconClick()
                        },
                    contentAlignment = Alignment.Center,

                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.cart_icon),
                        contentDescription = "Cart Icon"
                    )
                }
                Box(modifier = Modifier
                    .size(20.dp)
                    .background(color = Color.Red, shape = CircleShape)
                    .padding(3.dp)
                    .constrainAs(notificationCounter) {
                        top.linkTo(notification.top)
                        end.linkTo(notification.end)
                    },
                    contentAlignment = Alignment.Center,
                ){
                    if(state!=null){
                        Text(text = "${state?.itemList!!.size}", fontSize = 11.sp, color = Color.White)
                    }
                    else{
                        Text(text = "0", fontSize = 11.sp, color = Color.White)
                    }
                }
            }

        }
    }

}