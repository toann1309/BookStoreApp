package com.eritlab.jexmon.presentation.screens.sign_in_screen.component


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.internal.isLiveLiteralsEnabled
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.eritlab.jexmon.R
import com.eritlab.jexmon.domain.use_case.user_login.UserLoginUseCase
import com.eritlab.jexmon.presentation.common.CustomDefaultBtn
import com.eritlab.jexmon.presentation.common.CustomTextField
import com.eritlab.jexmon.presentation.component.DefaultBackArrow
import com.eritlab.jexmon.presentation.component.ErrorSuggestion
import com.eritlab.jexmon.presentation.graphs.auth_graph.AuthScreen
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.TextColor


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LoginScreen(navController: NavController, viewModel: UserLoginViewModel = hiltViewModel()
) {
    val state by viewModel.loginResponse.collectAsState()
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    val emailErrorState = remember {
        mutableStateOf(false)
    }
    val passwordErrorState = remember {
        mutableStateOf(false)
    }
    val emailErrorStateMessage = remember{
        mutableStateOf("")
    }
    val passwordErrorStateMessage = remember{
        mutableStateOf("")
    }
    val loadingState = remember { mutableStateOf(false) }
    val errorState = remember { mutableStateOf("") }
    val ctx = LocalContext.current
    val shareReference = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
    val editor = shareReference.edit()
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
        )
        {
            Box(modifier = Modifier.weight(0.7f)) {
                DefaultBackArrow {
                    navController.popBackStack()
                }
            }
            Box(modifier = Modifier.weight(1.0f)) {
                Text(text = "Sign in", color = MaterialTheme.colors.TextColor, fontSize = 18.sp)
            }


        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Welcome Back", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Sign in with your email or password.",
            color = MaterialTheme.colors.TextColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(50.dp))
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
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            placeholder = "********",
            trailingIcon = R.drawable.lock,
            label = "Password",
            keyboardType = KeyboardType.Password,
            errorState = passwordErrorState,
            visualTransformation = PasswordVisualTransformation()
        ) { newPass ->
            password = newPass
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (emailErrorState.value) {
            ErrorSuggestion(emailErrorStateMessage.value)
        }
        if (passwordErrorState.value) {
            Row() {
                ErrorSuggestion(passwordErrorStateMessage.value)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Forget Password",
                color = MaterialTheme.colors.TextColor,
                style = TextStyle(textDecoration = TextDecoration.Underline),
                modifier = Modifier.clickable {
                    navController.navigate(AuthScreen.ForgetPasswordScreen.route)
                }
            )
        }
        CustomDefaultBtn(shapeSize = 50f, btnText = "Sign In") {
            //email pattern
            val pattern = Patterns.EMAIL_ADDRESS
            val isEmailValid = pattern.matcher(email.text).matches()
            val isPassValid = password.text.length >= 6
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

            if(password.text.isBlank()){
                if(passwordErrorState.value == false){
                    passwordErrorState.value=true
                    passwordErrorStateMessage.value="Password must have input"
                }
            }
            else{
                if(0<password.text.length&&password.text.length<6){
                    passwordErrorState.value=true
                    passwordErrorStateMessage.value="Password at least 6 character"
                }else{
                    passwordErrorState.value=false
                }
            }
            if (isEmailValid && isPassValid) {

                viewModel.login(email.text,password.text)
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Don't have an account? ", color = MaterialTheme.colors.TextColor)
                Text(
                    text = "Sign Up",
                    color = MaterialTheme.colors.PrimaryColor,
                    modifier = Modifier.clickable {
                        navController.navigate(AuthScreen.SignUpScreen.route)
                    })
            }
        }
        LaunchedEffect(state){
//            loadingState.value = true
            if(state!=null){
                Log.e("Sau đây", state!!.status)
                if(state!!.status == "Mật khẩu sai"){
                    errorState.value = "Mật khẩu sai"
                    Toast.makeText(ctx,errorState.value,Toast.LENGTH_SHORT).show()

                }
                if(state!!.status == "Email này chưa đăng ký"){
                    errorState.value = "Email này chưa đăng ký"
                    Toast.makeText(ctx,errorState.value,Toast.LENGTH_SHORT).show()
                }
                if(state!!.status == "Tài khoản chưa được kích hoạt"){
                    errorState.value = "Tài khoản chưa được kích hoạt"
                    Toast.makeText(ctx,errorState.value,Toast.LENGTH_SHORT).show()
                }
                if(state!!.status == "Đăng nhập thành công"){

                    editor.putInt("id",state!!.id)
                    editor.apply()
                    Toast.makeText(ctx,state!!.status,Toast.LENGTH_LONG).show()
                    navController.navigate(AuthScreen.SignInSuccess.route)
                }
                loadingState.value=true
            }
            loadingState.value=false
        }
        if(loadingState.value){
            CircularProgressIndicator()
        }
    }

}


