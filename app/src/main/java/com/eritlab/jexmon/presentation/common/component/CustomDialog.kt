package com.eritlab.jexmon.presentation.common.component

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.eritlab.jexmon.R
import com.eritlab.jexmon.common.Constrains
import com.eritlab.jexmon.presentation.graphs.filter_graph.FilterGameScreen

private var platform = ""
private var category =""
private var sortBy = ""

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun CustomDialog(
    title:String,
    onDismiss:()->Unit,
    onPositiveButtonClicked:()->Unit,
    onNegativeButtonClicked:()->Unit,
    properties: DialogProperties = DialogProperties(),
    navController:NavController
){
    val ctx = LocalContext.current
    val array = mutableStateListOf<String>("","","")
    val filterList = listOf(
        ChipsModel(
            name = "platform",
            subList = listOf("browser", "mobile", "console"),
            trailingIcon = Icons.Default.ArrowDropDown,
            selectedIcon = Icons.Default.Check,
            order = 0,
        ),
        ChipsModel(
            name = "category",
            subList = listOf("mmorpg", "shooter","ARPG"),
            trailingIcon = Icons.Default.ArrowDropDown,
            selectedIcon = Icons.Default.Check,
            order = 1,
        ),
        ChipsModel(
            name = "sort-by",
            subList = listOf("release-date", "date-ago"),
            trailingIcon = Icons.Default.ArrowDropDown,
            selectedIcon = Icons.Default.Check,
            order = 2,
        ),
    )
    val selectedItems = mutableStateListOf<String>()
//    val selectedItems = mutableStateListOf<ChipsModel>()
    var isSelected by remember { mutableStateOf(false) }
    Dialog(
        onDismissRequest = onDismiss,
        properties = properties
    ) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .border(2.dp, color = Color(0xFFFF7643), shape = RoundedCornerShape(15.dp))
        ){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.close), contentDescription = "Close", modifier = Modifier
                    .align(Alignment.End)
                    .clickable(onClick = onDismiss))
                Spacer(modifier = Modifier.padding(top=15.dp))
                Text(text = title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
//                TutorialFilterChip()
                Column {
                    for (item in filterList){
                        isSelected = selectedItems.contains(item.name)
                        Spacer(modifier = Modifier.padding(5.dp))
                        if (item.subList != null) {
                            ChipWithSubItems(chipLabel = item.name, chipItems = item.subList, arrayList = array, order = item.order)
                        } else {
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    when (selectedItems.contains(item.name)) {
                                        true -> selectedItems.remove(item.name)
                                        false -> selectedItems.add(item.name)
                                    }
//                        selectedItems.sortBy {it.order}
                                },
                                content = {Text(text = item.name)},
                                leadingIcon = {
                                    val isCheckIcon = item.leadingIcon == Icons.Default.Check
                                    if (item.leadingIcon != null && isCheckIcon && isSelected) {
                                        Icon(item.leadingIcon, contentDescription = item.name)
                                    }
                                    if (item.leadingIcon != null && !isCheckIcon) {
                                        Icon(item.leadingIcon, contentDescription = item.name)
                                    }
                                },
                                trailingIcon = {
                                    if (item.trailingIcon != null && isSelected)
                                        Icon(item.trailingIcon, contentDescription = item.name)
                                },
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(top = 40.dp))
                Row(horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                    TextButton(onClick = onNegativeButtonClicked,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.Transparent,
                                contentColor = Color.Red
                            )
                        ) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = {
                        onPositiveButtonClicked
                            if(array[0] != "" && array[1] != "" && array[2] != ""){
                                navController.navigate(FilterGameScreen.FilterScreen.route + "/${array[0]}/${array[1]}/${array[2]}")
                            }else{
                                Toast.makeText(ctx, "Yêu cầu lọc tất cả", Toast.LENGTH_LONG).show()
                            }
                        },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFFF7643),
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "OK")
                            if(array[0]==""){
                                Log.e("Array", "rỗng mẹ rồi")
                            }
                            else{
                                Log.e("Array", array[0])
                                Log.e("Array", array[1])
                                Log.e("Array", array[2])
                            }
                    }
                }
            }
        }
    }
}
