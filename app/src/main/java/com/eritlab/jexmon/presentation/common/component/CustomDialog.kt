package com.eritlab.jexmon.presentation.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.eritlab.jexmon.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomDialog(
    title:String,
    onDismiss:()->Unit,
    onPositiveButtonClicked:()->Unit,
    onNegativeButtonClicked:()->Unit,
    properties: DialogProperties = DialogProperties()
){
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
                TutorialFilterChip()
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
                    Button(onClick = onPositiveButtonClicked,
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFFF7643),
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "OK")
                    }
                }
            }
        }
    }
}