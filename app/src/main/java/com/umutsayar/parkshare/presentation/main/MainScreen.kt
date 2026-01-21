package com.umutsayar.parkshare.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.umutsayar.parkshare.navigation.BottomNavItem
import com.umutsayar.parkshare.navigation.Screens
import com.umutsayar.parkshare.navigation.getOwnerBottomNavItems
import com.umutsayar.parkshare.navigation.getRenterBottomNavItems
import com.umutsayar.parkshare.presentation.parking.ParkingMapScreen

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
                ParkingMapScreen() // Senin hazırladığın harita ekranı
            }
            composable(Screens.Main.MyBookings.route) {
                PlaceholderScreen("Rezervasyonlarım ($userRole)")
            }
            composable(Screens.Main.AddListing.route) {
                PlaceholderScreen("İlan Ekleme Sayfası") // Owner ise buraya erişebilir
            }
            composable(Screens.Main.MyListings.route) {
                PlaceholderScreen("İlanlarım") // Owner ise buraya erişebilir
            }
            composable(Screens.Main.Favorites.route) {
                PlaceholderScreen("Favoriler") // Renter ise buraya erişebilir
            }
            composable(Screens.Main.Profile.route) {
                PlaceholderScreen("Profil Ayarları")
            }
        }
    }
}

// Test için geçici ekran
@Composable
fun PlaceholderScreen(title: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = title, style = MaterialTheme.typography.headlineMedium)
    }
}