package com.example.health_care.Screens

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.health_care.Components.CButton
import com.example.health_care.Components.CTextField
import com.example.health_care.Navigation.Screens
import com.example.health_care.R
import com.example.health_care.ViewModels.AuthViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
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

    var phone by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var genderExpanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf("Select") }
    val context = LocalContext.current


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
                CTextField(
                    hint = "Name",
                    value = name,
                    onValueChange = { name = it })
                TextField(
                    value = phone,
                    onValueChange = {
                        if (it.matches(Regex("^\\+?[0-9]*$")))
                            phone = it
                    },
                    placeholder = {
                        Text(
                            text = "Phone(+91...)",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily.Monospace,
                                color = Color.Black
                            )
                        )
                    },
                    textStyle = TextStyle(color = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color(0xFFBEC2C2),
                        unfocusedIndicatorColor = Color(0xFFBEC2C2)
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)


                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    OutlinedTextField(
                        value = dob,
                        onValueChange = { },
                        enabled = false,
                        label = { Text(
                            "DOB(YYYY-MM-DD)",
                            style = TextStyle(Color.Black)
                        ) },
                        textStyle = TextStyle(color = Color.Black),
                        readOnly = true,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = Color.Transparent,
                            focusedBorderColor = Color(0xFFBEC2C2),
                            unfocusedBorderColor = Color(0xFFBEC2C2)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()

                            .weight(0.6f)
                            .clickable {
                                val calendar = Calendar.getInstance()
                                DatePickerDialog(
                                    context,
                                    { _, year, month, day ->
                                        dob = String.format("%04d-%02d-%02d", year, month + 1, day)
                                    },
                                    calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DAY_OF_MONTH)
                                ).show()
                            }
                    )

                    Spacer(modifier = Modifier.width(16.dp))
                    ExposedDropdownMenuBox(
                        expanded = genderExpanded,
                        onExpandedChange = { genderExpanded = !genderExpanded },
                        modifier = Modifier.weight(0.4f)
                    ) {
                        OutlinedTextField(
                            value = selectedGender,
                            onValueChange = {},
                            label = { Text("Gender") },
                            textStyle = TextStyle(color = Color.Black),
                            readOnly = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = genderExpanded)
                            },
                            modifier = Modifier
                                .menuAnchor()
                                .fillMaxWidth()
                        )

                        ExposedDropdownMenu(
                            expanded = genderExpanded,
                            onDismissRequest = { genderExpanded = false }
                        ) {
                            listOf("Male", "Female", "Other").forEach { gender ->
                                DropdownMenuItem(
                                    text = { Text(gender) },
                                    onClick = {
                                        selectedGender = gender
                                        genderExpanded = false
                                    }
                                )
                            }
                        }
                    }


                }
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
 RegisterScreen()
}