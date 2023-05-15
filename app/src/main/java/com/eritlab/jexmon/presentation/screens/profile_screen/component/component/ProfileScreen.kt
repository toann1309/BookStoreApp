package com.eritlab.jexmon.presentation.screens.profile_screen.component

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.HorizontalAnchorable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.eritlab.jexmon.presentation.ui.theme.TextColor
import com.eritlab.jexmon.R
import com.eritlab.jexmon.presentation.component.DefaultBackArrow
import com.eritlab.jexmon.presentation.component.ModalUpdateProfile
import com.eritlab.jexmon.presentation.graphs.Graph
import com.eritlab.jexmon.presentation.graphs.auth_graph.AuthScreen
import com.eritlab.jexmon.presentation.graphs.home_graph.ShopHomeScreen
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: UserDetailViewModel = hiltViewModel()
) {
    val ctx = LocalContext.current
    val shareReference = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
    val id = shareReference.getInt("id",1)
    viewModel.getUser(id)
    val state by viewModel.userDetail.collectAsState()
    LaunchedEffect(state){
        if(state!=null){
            Log.e("name",state!!.firstName)
        }
    }
    var showModal by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        val isLoading = state
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(0.5f)) {
                DefaultBackArrow {
                    navController.popBackStack()
                }
            }
            Box(modifier = Modifier.weight(0.7f)) {
                Text(
                    text = "Profile",
                    color = MaterialTheme.colors.TextColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (image, cameraIcon) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.profile_image),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .clip(CircleShape)
                    .constrainAs(image) {
                        linkTo(start = parent.start, end = parent.end)
                    }
            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.constrainAs(cameraIcon) {
                bottom.linkTo(image.bottom)
                end.linkTo(image.end)

            }) {

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera_icon),
                        contentDescription = "Change Picture",
                        modifier = Modifier.background(Color.LightGray)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "${state?.firstName} ${state?.lastName}", color = Color(0xFFFF7643), fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)

                .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    showModal = true
                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.user_icon),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colors.PrimaryColor
            )
            Text("Profile Picture", modifier = Modifier.weight(0.2f))
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colors.TextColor
            )
        }

        Spacer(modifier = Modifier.height(15.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .clickable {
//                    navController.navigate(AuthScreen.SignInScreen.route)
                    Toast
                        .makeText(ctx, "Chức năng chưa làm", Toast.LENGTH_LONG)
                        .show()
                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.log_out),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colors.PrimaryColor
            )
            Text("Logout", modifier = Modifier.weight(0.2f))
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colors.TextColor
            )
        }
    }

    if(showModal){
        ModalUpdateProfile(
            onDismiss = { showModal = false},
            onPositiveButtonClicked = {
                showModal=false
                Toast.makeText(ctx,"Update Successfully", Toast.LENGTH_LONG).show();
            },
            onNegativeButtonClicked = {
                showModal=false
                Toast.makeText(ctx,"Cancel updated", Toast.LENGTH_LONG).show();
            })
    }
}