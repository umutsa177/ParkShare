package com.umutsayar.parkshare.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// User Role Enum
enum class UserRole {
    OWNER,
    RENTER
}

@Composable
fun ParkShareBottomNavigation(
    currentRoute: String,
    userRole: UserRole,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        // Harita (Herkes)
        NavigationBarItem(
            selected = currentRoute == "map",
            onClick = { onNavigate("map") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Map,
                    contentDescription = "Harita",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = "Harita",
                    fontSize = 11.sp
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF2196F3),
                selectedTextColor = Color(0xFF2196F3),
                indicatorColor = Color(0xFF2196F3).copy(alpha = 0.1f),
                unselectedIconColor = Color(0xFF9E9E9E),
                unselectedTextColor = Color(0xFF9E9E9E)
            )
        )

        // Rezervasyonlarım (Herkes)
        NavigationBarItem(
            selected = currentRoute == "bookings",
            onClick = { onNavigate("bookings") },
            icon = {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "Rezervasyonlarım",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = "Geçmiş",
                    fontSize = 11.sp
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF2196F3),
                selectedTextColor = Color(0xFF2196F3),
                indicatorColor = Color(0xFF2196F3).copy(alpha = 0.1f),
                unselectedIconColor = Color(0xFF9E9E9E),
                unselectedTextColor = Color(0xFF9E9E9E)
            )
        )

        // Orta Buton (Role'e göre değişir)
        NavigationBarItem(
            selected = when (userRole) {
                UserRole.OWNER -> currentRoute == "add_listing"
                UserRole.RENTER -> currentRoute == "favorites"
            },
            onClick = {
                when (userRole) {
                    UserRole.OWNER -> onNavigate("add_listing")
                    UserRole.RENTER -> onNavigate("favorites")
                }
            },
            icon = {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2196F3)),
                    contentAlignment = Alignment.Center
                ) {
                    when (userRole) {
                        UserRole.OWNER -> {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "İlan Ekle",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        UserRole.RENTER -> {
                            Text(
                                text = "P",
                                style = MaterialTheme.typography.headlineMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.White
                            )
                        }
                    }
                }
            },
            label = { },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Transparent,
                unselectedIconColor = Color.Transparent,
                indicatorColor = Color.Transparent
            )
        )

        // İlanlarım (Owner) / Favoriler (Renter)
        if (userRole == UserRole.OWNER) {
            NavigationBarItem(
                selected = currentRoute == "listings",
                onClick = { onNavigate("listings") },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Garage,
                        contentDescription = "İlanlarım",
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = "İlanlarım",
                        fontSize = 11.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF2196F3),
                    selectedTextColor = Color(0xFF2196F3),
                    indicatorColor = Color(0xFF2196F3).copy(alpha = 0.1f),
                    unselectedIconColor = Color(0xFF9E9E9E),
                    unselectedTextColor = Color(0xFF9E9E9E)
                )
            )
        } else {
            // Renter için Favoriler
            NavigationBarItem(
                selected = currentRoute == "favorites",
                onClick = { onNavigate("favorites") },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favoriler",
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = "Favoriler",
                        fontSize = 11.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF2196F3),
                    selectedTextColor = Color(0xFF2196F3),
                    indicatorColor = Color(0xFF2196F3).copy(alpha = 0.1f),
                    unselectedIconColor = Color(0xFF9E9E9E),
                    unselectedTextColor = Color(0xFF9E9E9E)
                )
            )
        }

        // Profil (Herkes)
        NavigationBarItem(
            selected = currentRoute == "profile",
            onClick = { onNavigate("profile") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profil",
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = "Profil",
                    fontSize = 11.sp
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF2196F3),
                selectedTextColor = Color(0xFF2196F3),
                indicatorColor = Color(0xFF2196F3).copy(alpha = 0.1f),
                unselectedIconColor = Color(0xFF9E9E9E),
                unselectedTextColor = Color(0xFF9E9E9E)
            )
        )
    }
}