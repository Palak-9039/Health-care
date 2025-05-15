package com.example.health_care.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.health_care.Screens.AppointmentScreen
import com.example.health_care.Screens.Dashboard
import com.example.health_care.Screens.LoginScreen
import com.example.health_care.Screens.ProfileScreen
import com.example.health_care.Screens.RegisterScreen
import com.example.health_care.Screens.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Login.route) {
        composable(Screens.Login.route){
            LoginScreen(navController)
        }
        composable(Screens.Register.route){
            RegisterScreen(navController)
        }
        composable(Screens.Appointment.route){
            AppointmentScreen(navController)
        }
        composable(Screens.Dashboard.route){
            Dashboard(navController)
        }
        composable(Screens.Splash.route){
            SplashScreen(navController)
        }
        composable(Screens.Profile.route){
            ProfileScreen(navController)
        }
    }
}
