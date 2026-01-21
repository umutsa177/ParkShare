package com.umutsayar.parkshare.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val ownerOnly: Boolean = false
) {
    object Map : BottomNavItem(
        route = "map",
        title = "Harita",
        icon = Icons.Default.Map
    )

    object MyBookings : BottomNavItem(
        route = "my_bookings",
        title = "Rezervasyonlarım",
        icon = Icons.Default.BookOnline
    )

    object AddListing : BottomNavItem(
        route = "add_listing",
        title = "İlan Ekle",
        icon = Icons.Default.AddCircle,
        ownerOnly = true
    )

    object MyListings : BottomNavItem(
        route = "my_listings",
        title = "İlanlarım",
        icon = Icons.Default.Garage,
        ownerOnly = true
    )

    object Favorites : BottomNavItem(
        route = "favorites",
        title = "Favoriler",
        icon = Icons.Default.Favorite
    )

    object Profile : BottomNavItem(
        route = "profile",
        title = "Profil",
        icon = Icons.Default.Person
    )
}

// OWNER için bottom nav items
fun getOwnerBottomNavItems() = listOf(
    BottomNavItem.Map,
    BottomNavItem.MyBookings,
    BottomNavItem.AddListing,
    BottomNavItem.MyListings,
    BottomNavItem.Profile
)

// RENTER için bottom nav items
fun getRenterBottomNavItems() = listOf(
    BottomNavItem.Map,
    BottomNavItem.MyBookings,
    BottomNavItem.Favorites,
    BottomNavItem.Profile
)