package com.eritlab.jexmon.presentation.dashboard_screen.component


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.presentation.screens.checkout_screen.component.component.CustomDialog
import com.eritlab.jexmon.presentation.graphs.filter_graph.FilterGameScreen
import com.eritlab.jexmon.presentation.graphs.option_graph.OptionScreen
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor

@Composable
fun FilterButton(
    navController: NavController,
    isVisible:Boolean
){
    var showDialogs by remember{
        mutableStateOf(false)
    }
    val ctx = LocalContext.current
    if(isVisible){
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
                    Icon(painter = painterResource(id = com.eritlab.jexmon.R.drawable.filter_svgrepo_com), contentDescription = "Filter Icon")
                }
            }
        }
    }
    if (showDialogs){
        CustomDialog(
            title = "Filter",
            onDismiss = { showDialogs=false },
            onPositiveButtonClicked = {

                Toast.makeText(ctx,"Positive button clicked", Toast.LENGTH_LONG).show();
            },
            onNegativeButtonClicked = {
                showDialogs=false
                Toast.makeText(ctx,"Negative Button clicked", Toast.LENGTH_LONG).show();

            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            navController = navController
        )
    }

}