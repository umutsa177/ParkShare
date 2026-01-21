package com.umutsayar.parkshare.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.umutsayar.parkshare.navigation.Screens
import com.umutsayar.parkshare.navigation.getOwnerBottomNavItems
import com.umutsayar.parkshare.navigation.getRenterBottomNavItems
import com.umutsayar.parkshare.presentation.add_listing.AddListingScreen
import com.umutsayar.parkshare.presentation.bookings.MyBookingsScreen
import com.umutsayar.parkshare.presentation.bookings.model.BookingModel
import com.umutsayar.parkshare.presentation.bookings.model.BookingStatus
import com.umutsayar.parkshare.presentation.parking.ParkingMapScreen
import com.umutsayar.parkshare.presentation.profile.ProfileScreen

@Composable
fun MainScreen(userRole: String) {
    val navController = rememberNavController()

    // Role göre menü listesini belirle
    val navItems = remember(userRole) {
        if (userRole == "OWNER") getOwnerBottomNavItems() else getRenterBottomNavItems()
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                navItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Bottom Bar Rotalarının İçeriği
        NavHost(
            navController = navController,
            startDestination = Screens.Main.Map.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.Main.Map.route) {
                ParkingMapScreen()
            }
            composable(Screens.Main.MyBookings.route) {
                // Test verisi (Mock Data)
                val mockBookings = listOf(
                    BookingModel(
                        title = "Rezervasyon 1",
                        address = "İstanbul, Kadıköy",
                        dateTime = "15 Oca 2026",
                        price = "450₺",
                        status = BookingStatus.PENDING,
                        imageRes = 0,
                    ),
                    BookingModel(
                        title = "Rezervasyon 2",
                        address = "İstanbul, Sarıyer",
                        dateTime = "15",
                        price = "400₺",
                        status = BookingStatus.APPROVED,
                        imageRes = 0,
                    )
                )

                MyBookingsScreen(
                    bookings = mockBookings,
                    onSearchPark = {
                        navController.navigate(Screens.Main.Map.route)
                    },
                    onDetailClick = { /* Detay sayfasına git */ }
                )
            }
            composable(Screens.Main.AddListing.route) {
                AddListingScreen() // Owner ise buraya erişebilir
            }
            composable(Screens.Main.MyListings.route) {
                SimplePlaceholderScreen("İlanlarım", "Henüz bir ilan eklemediniz.") // Owner ise buraya erişebilir
            }
            composable(Screens.Main.Favorites.route) {
                SimplePlaceholderScreen("Favoriler", "Favori park yeriniz yok.")   // Renter ise buraya erişebilir
            }
            composable(Screens.Main.Profile.route) {
                ProfileScreen(
                    onLogout = { /* Logout işlemi */ },
                    onNavigateToEditProfile = { /* Navigasyon */ },
                    onNavigateToNotifications = { /* Navigasyon */ },
                    onNavigateToPaymentMethods = { /* Navigasyon */ },
                    onNavigateToHelp = { /* Navigasyon */ }
                )
            }
        }
    }
}

@Composable
fun SimplePlaceholderScreen(title: String, subtitle: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.foundation.layout.Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, style = MaterialTheme.typography.headlineMedium)
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(8.dp))
            Text(text = subtitle, color = androidx.compose.ui.graphics.Color.Gray)
        }
    }
}