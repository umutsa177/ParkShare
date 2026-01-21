package com.umutsayar.parkshare.navigation

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")

    object MapScreen : Screens("map_screen")

    object LoginScreen : Screens("login_screen")

    object ProfileScreen : Screens("profile_screen")

    object AddParkScreen : Screens("add_park_screen")

    object ParkDetailScreen : Screens("park_detail_screen")
}
