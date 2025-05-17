package com.example.health_care.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.health_care.Components.CButton
import com.example.health_care.Components.CTextField
import com.example.health_care.Navigation.Screens
import com.example.health_care.R
import com.example.health_care.ViewModels.AuthViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel
){
    var email by remember{ mutableStateOf("")}
    var name by remember{ mutableStateOf("")}
    var password by remember{ mutableStateOf("")}
    var confirmPassword by remember{ mutableStateOf("")}

    val firebaseUser by authViewModel.firebaseUser.observeAsState(null)
    val error by authViewModel.error.observeAsState()
    val isLoading by authViewModel.isLoading.observeAsState(false)


    LaunchedEffect(firebaseUser){
        if(firebaseUser!=null){
            navController.popBackStack()
            navController.navigate(Screens.Dashboard.route){
                popUpTo(Screens.Register.route){
                    inclusive = true
                }
                launchSingleTop = true
            }
        }
    }

    if(isLoading==true){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }else {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.image2),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxSize()

            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.opera_news_personalized_news_2019_07_23),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(top = 55.dp)
                        .height(100.dp)
                        .offset(x = (-20).dp)

                )
                Text(
                    text = "Sign Up ",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily.Monospace,
                        fontWeight = FontWeight(500),
                        color = Color.Black
                    ),
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    text = "Sign Up now to book appointments with the best Doctors",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Default,
                        color = Color.Black
                    ),
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 25.dp)
                )
                CTextField(hint = "Name", value = name, onValueChange = { name = it })
                CTextField(
                    hint = "Email",
                    value = email,
                    onValueChange = { email = it }) //Custom text field function
                CTextField(
                    hint = "Enter Password",
                    value = password,
                    onValueChange = { password = it })
                CTextField(hint = "Confirm Password", value = confirmPassword, onValueChange = {confirmPassword = it})// called from Components
                Spacer(modifier = Modifier.weight(1f))
                if(password!=confirmPassword){
                    Text(text = "Password does not match!!", color = Color.Red, fontSize = 12.sp, fontWeight = FontWeight.Thin)
                }else{
                    CButton(
                        text = "Sign Up",
                        onClick = {
                            authViewModel.register(email, password,name) }
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 12.dp, bottom = 50.dp),
                ) {
                    Text(
                        text = "Already have an account?",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(500),
                            fontFamily = FontFamily.Monospace,
                            color = Color(0xFF000000)
                        )
                    )
                    Text(
                        modifier = Modifier.clickable { navController.navigate(Screens.Login.route) },
                        text = "Sign in",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight(800),
                            fontFamily = FontFamily.Monospace,
                            color = Color.Red
                        )
                    )
                }

            }
        }
    }
}

@Preview
@Composable
private fun SignUpScreenPreview() {
//    RegisterScreen()
}