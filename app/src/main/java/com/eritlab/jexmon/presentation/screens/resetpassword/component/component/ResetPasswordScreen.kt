package com.eritlab.jexmon.presentation.screens.resetpassword.component

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eritlab.jexmon.R
import com.eritlab.jexmon.presentation.common.CustomDefaultBtn
import com.eritlab.jexmon.presentation.common.CustomTextField
import com.eritlab.jexmon.presentation.screens.checkout_screen.component.component.DefaultBackArrow
import com.eritlab.jexmon.presentation.screens.checkout_screen.component.component.ErrorSuggestion
import com.eritlab.jexmon.presentation.graphs.auth_graph.AuthScreen
import com.eritlab.jexmon.presentation.ui.theme.TextColor

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    viewModel: ResetPasswordViewModel = hiltViewModel()
){
    val ctx = LocalContext.current
    val shareReference = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
    val state by viewModel.resetPasswordResponse.collectAsState()
    var passNew by remember { mutableStateOf(TextFieldValue("")) }
    var otp by remember { mutableStateOf(TextFieldValue("")) }
    val email = shareReference.getString("emailReset","")

    val passwordErrorState = remember {
        mutableStateOf(false)
    }
    val passwordErrorStateMessage = remember {
        mutableStateOf("")
    }
    val otpErrorState = remember{ mutableStateOf(false) }
    val otpErrorStateMessage = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth()
                .offset(y = 0.dp)
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(0.4f)) {
                DefaultBackArrow {
                    navController.popBackStack()
                }
            }
            Box(modifier = Modifier.weight(1.0f)) {
                Text(text = "Forget Password", color = MaterialTheme.colors.TextColor, fontSize = 18.sp)
            }
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Forget Password", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Please enter your new password and otp\nto make your new password",
            color = MaterialTheme.colors.TextColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CustomTextField(
                placeholder = "********",
                trailingIcon = R.drawable.lock,
                label = "Password",
                keyboardType = KeyboardType.Text,
                errorState = passwordErrorState,
                visualTransformation = VisualTransformation.None
            ) { newPass ->
                passNew = newPass
            }
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                placeholder = "152698",
                trailingIcon = R.drawable.phone,
                label = "OTP",
                keyboardType = KeyboardType.Number,
                errorState = otpErrorState,
                visualTransformation = VisualTransformation.None,
            ) { otpnumber ->
                otp = otpnumber
            }
            Spacer(modifier = Modifier.height(10.dp))
            if(passwordErrorState.value){
                ErrorSuggestion(passwordErrorStateMessage.value)
            }
            if(otpErrorState.value){
                Row(){
                    ErrorSuggestion(otpErrorStateMessage.value)
                }
            }
            CustomDefaultBtn(shapeSize = 50f, btnText = "Reset Password") {

                val isNewPassValid = passNew.text.length>=6
                val isOptValid = otp.text.length == 5


                if(otp.text.isBlank() ){

                    if(otpErrorState.value==false){
                        otpErrorState.value=true
                        otpErrorStateMessage.value="OTP must has input"
                    }
                }else{
                    if(otp.text.length > 0 && otp.text.length != 5){
                        otpErrorState.value = true
                        otpErrorStateMessage.value="OTP must be 5 character"
                    } else{
                        otpErrorState.value = false
                    }
                }
                if(passNew.text.isBlank()){
                    if(passwordErrorState.value == false){
                        passwordErrorState.value=true
                        passwordErrorStateMessage.value="Password must have input"
                    }
                } else{
                    if(0<passNew.text.length&&passNew.text.length<6){
                        passwordErrorState.value=true
                        passwordErrorStateMessage.value="Password at least 6 character"
                    }else{
                        passwordErrorState.value=false
                    }
                }
            //                passwordErrorState.value = !isNewPassValid
            //                otpErrorState.value = !isOptValid
                if (isNewPassValid && isOptValid) {
                    viewModel.resetPassword(email.toString(), passNew.text,otp.text)
//                    navController.navigate(AuthScreen.SignInScreen.route)
                }
            }
        }
        LaunchedEffect(state){
            if(state!=null){
                Log.e("email Reset Ctx", email.toString())
                Log.e("status Reset", state!!.status)
                navController.navigate(AuthScreen.SignInScreen.route)
            }
        }
    }
}