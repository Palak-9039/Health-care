package com.example.health_care.Navigation

sealed class Screens(
    val route : String
) {
object Login:Screens("login")
object Register:Screens("register")
object Dashboard:Screens("dashboard")
object Appointment:Screens("appointment")
object Splash:Screens("splash")
object Profile:Screens("profile")
}