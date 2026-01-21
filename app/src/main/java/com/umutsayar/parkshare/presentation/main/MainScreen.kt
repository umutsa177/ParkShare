package com.umutsayar.parkshare.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.umutsayar.parkshare.navigation.*

enum class UserRole {
    OWNER,
    RENTER
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // Bu değeri authentication sisteminden alacaksınız
    // Şimdilik örnek olarak OWNER kullanıyorum
    var userRole by remember { mutableStateOf(UserRole.OWNER) }

    val bottomNavItems = if (userRole == UserRole.OWNER) {
        getOwnerBottomNavItems()
    } else {
        getRenterBottomNavItems()
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                bottomNavItems.forEach { item ->
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
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Map.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomNavItem.Map.route) {
                MapScreen(
                    onSpotClick = { spotId ->
                        // Spot detail ekranına git
                    }
                )
            }

            composable(BottomNavItem.MyBookings.route) {
                MyBookingsScreen(
                    userRole = userRole
                )
            }

            if (userRole == UserRole.OWNER) {
                composable(BottomNavItem.AddListing.route) {
                    AddListingScreen(
                        onListingAdded = {
                            navController.navigate(BottomNavItem.MyListings.route)
                        }
                    )
                }

                composable(BottomNavItem.MyListings.route) {
                    MyListingsScreen(
                        onListingClick = { listingId ->
                            // İlan detay ekranına git
                        }
                    )
                }
            } else {
                composable(BottomNavItem.Favorites.route) {
                    FavoritesScreen(
                        onSpotClick = { spotId ->
                            // Spot detail ekranına git
                        }
                    )
                }
            }

            composable(BottomNavItem.Profile.route) {
                ProfileScreen(
                    userRole = userRole,
                    onLogout = {
                        // Ana NavController'a logout sinyali gönder
                    }
                )
            }
        }
    }
}