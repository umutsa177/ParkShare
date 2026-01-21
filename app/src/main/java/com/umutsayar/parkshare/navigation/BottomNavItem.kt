package com.umutsayar.parkshare.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Map : BottomNavItem(Screens.Main.Map.route, "Harita", Icons.Default.Map)
    object MyBookings : BottomNavItem(Screens.Main.MyBookings.route, "Rezervasyon", Icons.Default.DateRange)
    object AddListing : BottomNavItem(Screens.Main.AddListing.route, "İlan Ekle", Icons.Default.AddCircle)
    object MyListings : BottomNavItem(Screens.Main.MyListings.route, "İlanlarım", Icons.Default.List)
    object Favorites : BottomNavItem(Screens.Main.Favorites.route, "Favoriler", Icons.Default.Favorite)
    object Profile : BottomNavItem(Screens.Main.Profile.route, "Profil", Icons.Default.Person)
}

// OWNER Menüsü: [Harita] [Rezervasyonlarım] [İlan Ekle] [İlanlarım] [Profil]
fun getOwnerBottomNavItems() = listOf(
    BottomNavItem.Map,
    BottomNavItem.MyBookings,
    BottomNavItem.AddListing,
    BottomNavItem.MyListings,
    BottomNavItem.Profile
)

// RENTER Menüsü: [Harita] [Rezervasyonlarım] [Favoriler] [Profil]
fun getRenterBottomNavItems() = listOf(
    BottomNavItem.Map,
    BottomNavItem.MyBookings,
    BottomNavItem.Favorites,
    BottomNavItem.Profile
)