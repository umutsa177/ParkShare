package com.umutsayar.parkshare.navigation

sealed class Screens(val route: String) {
    object SplashScreen : Screens("splash_screen")

    sealed class Auth(val route: String) {
        object Root : Auth("auth_root")
        object Login : Auth("login_screen")
        object Register : Auth("register_screen")
    }

    sealed class Main(val route: String) {
        object Root : Main("main_root/{userRole}") {
            fun createRoute(userRole: String) = "main_root/$userRole"
        }
        // Bottom Nav Rotaları
        object Map : Main("map_screen")
        object MyBookings : Main("my_bookings_screen")
        object AddListing : Main("add_listing_screen")
        object MyListings : Main("my_listings_screen")
        object Favorites : Main("favorites_screen")
        object Profile : Main("profile_screen")
    }
}