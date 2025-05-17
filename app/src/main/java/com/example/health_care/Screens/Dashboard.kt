package com.example.health_care.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.health_care.Components.CButton
import com.example.health_care.ViewModels.AuthViewModel

@Composable
fun Dashboard(
    navController: NavController,
    authViewModel: AuthViewModel){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Dashboard")
        CButton(text = "Logout", onClick = {authViewModel.logout()})
    }

}