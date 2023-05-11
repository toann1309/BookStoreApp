package com.eritlab.jexmon.presentation.screens.sign_up_screen.component

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
import com.eritlab.jexmon.presentation.ui.theme.PrimaryColor
import com.eritlab.jexmon.presentation.ui.theme.TextColor
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel:SignUpViewModel = hiltViewModel()) {
    val ctx= LocalContext.current
    val state by viewModel.registerResponse.collectAsState()
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    val emailErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val firstNameErrorState = remember { mutableStateOf(false) }
    val lastNameErrorState = remember { mutableStateOf(false) }
    val phoneNumberErrorState = remember { mutableStateOf(false) }
    val addressErrorState = remember { mutableStateOf(false) }
    val emailErrorStateMessage = remember { mutableStateOf("") }
    val passwordErrorStateMessage = remember { mutableStateOf("") }
    val firstNameErrorStateMessage = remember { mutableStateOf("") }
    val lastNameErrorStateMessage = remember { mutableStateOf("") }
    val phoneNumberErrorStateMessage = remember { mutableStateOf("") }
    val addressErrorStateMessage = remember { mutableStateOf("") }
    val loadingState = remember { mutableStateOf(false) }
    val errorState = remember { mutableStateOf("") }
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
                .fillMaxWidth(),
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
                Text(
                    text = "Sign Up",
                    color = MaterialTheme.colors.TextColor,
                    fontSize = 18.sp
                )
            }


        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Register Account", fontSize = 26.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(
            text = "Complete your details or continue\nwith social media.",
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


        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            placeholder = "Enter your first name",
            trailingIcon = R.drawable.user,
            label = "First Name",
            errorState = firstNameErrorState,
            keyboardType = KeyboardType.Text,
            visualTransformation = VisualTransformation.None
        ) { newText ->
            firstName = newText
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            placeholder = "Enter your last name",
            trailingIcon = R.drawable.user,
            label = "Last Name",
            errorState = lastNameErrorState,
            keyboardType = KeyboardType.Text,
            visualTransformation = VisualTransformation.None
        ) { newText ->
            lastName = newText
        }

        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            placeholder = "Enter your phone number",
            trailingIcon = R.drawable.phone,
            label = "Phone Number",
            keyboardType = KeyboardType.Phone,
            errorState = phoneNumberErrorState,
            visualTransformation = VisualTransformation.None
        ) { newNumber ->
            phoneNumber = newNumber
        }


        Spacer(modifier = Modifier.height(20.dp))
        CustomTextField(
            placeholder = "example: Dhaka, Bangladesh",
            trailingIcon = R.drawable.location_point,
            label = "Address",
            keyboardType = KeyboardType.Password,
            errorState = addressErrorState,
            visualTransformation = VisualTransformation.None
        ) { newText ->
            address = newText
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
        if (lastNameErrorState.value) {
            ErrorSuggestion(lastNameErrorStateMessage.value)
        }
        if (firstNameErrorState.value) {
            ErrorSuggestion(firstNameErrorStateMessage.value)
        }
        if (phoneNumberErrorState.value) {
            ErrorSuggestion(phoneNumberErrorStateMessage.value)
        }
        if (addressErrorState.value) {
            ErrorSuggestion(addressErrorStateMessage.value)
        }
        CustomDefaultBtn(shapeSize = 50f, btnText = "Continue") {
            //email pattern
            val pattern = Patterns.EMAIL_ADDRESS
            val isEmailValid = pattern.matcher(email.text).matches()
            val isPassValid = password.text.length >= 6
            val isPhoneValid = phoneNumber.text.length > 0
            val isFNameValid = firstName.text.length > 0
            val isLNameValid = lastName.text.length > 0
            val isAddressValid = address.text.length > 0
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
            if(firstName.text.isBlank()){
                if(firstNameErrorState.value==false){
                    firstNameErrorState.value=true
                    firstNameErrorStateMessage.value="First Name must have input"
                }
            }
            else{
                firstNameErrorState.value=false
            }
            if(lastName.text.isBlank()){
                if(lastNameErrorState.value==false){
                    lastNameErrorState.value=true
                    lastNameErrorStateMessage.value="First Name must have input"
                }
            }
            else{
                lastNameErrorState.value=false
            }
            if(phoneNumber.text.isBlank()){
                if(phoneNumberErrorState.value==false){
                    phoneNumberErrorState.value=true
                    phoneNumberErrorStateMessage.value="First Name must have input"
                }
            }
            else{
                phoneNumberErrorState.value=false
            }
            if(address.text.isBlank()){
                if(addressErrorState.value==false){
                    addressErrorState.value=true
                    addressErrorStateMessage.value="First Name must have input"
                }
            }
            else{
                addressErrorState.value=false
            }
            if (isEmailValid && isPassValid && isFNameValid && isLNameValid && isAddressValid && isPhoneValid) {
                editor.putString("email",email.text)
                editor.apply()
                viewModel.register(firstName.text, lastName.text, email.text, password.text, address.text,phoneNumber.text)

            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .clickable {

                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "By continuing you confirm that you agree",
                    color = MaterialTheme.colors.TextColor,
                    fontSize = 12.sp
                )
                Row()
                {
                    Text(
                        text = "with our ",
                        color = MaterialTheme.colors.TextColor,
                        fontSize = 12.sp
                    )
                    Text(
                        text = "Terms & Condition",
                        color = MaterialTheme.colors.PrimaryColor,
                        fontSize = 12.sp,
                        modifier = Modifier.clickable {
                        })
                }
            }
        }
        LaunchedEffect(state){
            loadingState.value = true
            delay(5000)
            if(state!=null){
                Log.e("Register",state!!.email)
                loadingState.value=true
                navController.navigate(AuthScreen.OTPScreen.route)
            }
            loadingState.value = false
        }
        LaunchedEffect(loadingState){
            loadingState.value = !loadingState.value
        }
        if(loadingState.value){
            CircularProgressIndicator()
        }
//        if(state!=null){
//
//
//            if(loadingState.value == true){
//                loadingState.value = false
//                LaunchedEffect(Unit){
//                    try{
//                        navController.navigate(AuthScreen.OTPScreen.route)
//                    }
//                }
//            }
//        }
    }
}


