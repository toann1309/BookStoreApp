package com.eritlab.jexmon.presentation.screens.forget_password_screen.component

import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import com.eritlab.jexmon.presentation.component.DefaultBackArrow
import com.eritlab.jexmon.presentation.component.ErrorSuggestion
import com.eritlab.jexmon.presentation.graphs.auth_graph.AuthScreen
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.TextColor


@Composable
fun ForgetPasswordScreen(
    navController: NavController,
    viewModel: ForgotPasswordViewModel = hiltViewModel()
) {
    val ctx= LocalContext.current
    val state by viewModel.forgetPasswordResponse.collectAsState()
    var email by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val emailErrorState = remember {
        mutableStateOf(false)
    }
    val emailErrorStateMessage = remember {
        mutableStateOf("")
    }
    val shareReference = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
    val editor = shareReference.edit()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {

            Box(modifier = Modifier.weight(0.4f)) {
                DefaultBackArrow() {
                    navController.popBackStack()
                }
            }
            Box(modifier = Modifier.weight(1.0f)) {
                Text(
                    text = "Forget Password",
                    color = MaterialTheme.colors.TextColor,
                    fontSize = 18.sp
                )
            }


        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Forget Password", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Please enter your email and we will send\nyou a link to return your account",
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
                placeholder = "example@email.com",
                trailingIcon = R.drawable.mail,
                label = "Email",
                errorState = emailErrorState,
                keyboardType = KeyboardType.Email,
                visualTransformation = VisualTransformation.None
            ) { newEmail ->
                email = newEmail
            }
            Spacer(modifier = Modifier.height(10.dp))
            if(emailErrorState.value){
                ErrorSuggestion(emailErrorStateMessage.value)
            }
            CustomDefaultBtn(shapeSize = 50f, btnText = "Continue") {
                //email pattern
                val pattern = Patterns.EMAIL_ADDRESS
                val isEmailValid = pattern.matcher(email.text).matches()
//                emailErrorState.value = !isEmailValid
                if(email.text.isBlank()){
                    if(emailErrorState.value==false){
                        emailErrorState.value=true
                        emailErrorStateMessage.value="Email must have input"
                    }
                }else{
                    if(!isEmailValid){
                        emailErrorState.value=true
                        emailErrorStateMessage.value="Email is false format"
                    }else{
                        emailErrorState.value=false
                    }
                }
                if(isEmailValid){
                    editor.putString("emailReset",email.text)
                    editor.apply()
                    viewModel.forgetPassword(email.text)

                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp, bottom = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don't have an account? ", color = MaterialTheme.colors.TextColor)
                Text(
                    text = "Sign Up",
                    color = MaterialTheme.colors.PrimaryColor,
                    modifier = Modifier.clickable {
                        navController.navigate(AuthScreen.ResetPasswordScreen.route)
                    })
            }
        }

        LaunchedEffect(state){
            if(state!=null){
                Log.e("email Reset",state!!.email);
                Toast.makeText(ctx,state!!.status,Toast.LENGTH_LONG).show()
                navController.navigate(AuthScreen.ResetPasswordScreen.route)
            }
        }
    }
}