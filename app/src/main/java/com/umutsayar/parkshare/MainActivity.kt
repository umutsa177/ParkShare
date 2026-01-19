package com.umutsayar.parkshare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.umutsayar.parkshare.presentation.auth.login.LoginScreen
import com.umutsayar.parkshare.presentation.auth.register.RegisterScreen
import com.umutsayar.parkshare.presentation.components.ParkShareBottomNavigation
import com.umutsayar.parkshare.presentation.components.UserRole
import com.umutsayar.parkshare.presentation.profile.ProfileScreen
import com.umutsayar.parkshare.presentation.splash.ParkShareSplashScreen
import com.umutsayar.parkshare.ui.theme.ParkShareTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParkShareTheme {
                ParkShareApp()
            }
        }
    }
}

@Composable
fun ParkShareApp() {
    val navController = rememberNavController()

    // Kullanıcı giriş durumu ve rolü (normalde ViewModel'den gelecek)
    var isLoggedIn by remember { mutableStateOf(false) }
    var userRole by remember { mutableStateOf(UserRole.RENTER) }

    // Current route'u takip et
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: "splash"

    // Bottom nav'in gösterilmesi gereken route'lar
    val bottomNavRoutes = listOf("map", "bookings", "add_listing", "listings", "favorites", "profile")
    val shouldShowBottomBar = currentRoute in bottomNavRoutes && isLoggedIn

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                ParkShareBottomNavigation(
                    currentRoute = currentRoute,
                    userRole = userRole,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            // Pop up to start destination of the graph to avoid building up a large stack
                            popUpTo("map") {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier
                .fillMaxSize()
                .padding(if (shouldShowBottomBar) paddingValues else paddingValues)
        ) {
            // ==================== AUTH FLOW ====================

            // Splash Screen
            composable("splash") {
                ParkShareSplashScreen()

                // 2 saniye sonra login'e yönlendir
                LaunchedEffect(Unit) {
                    delay(2000)
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            }

            // Login Screen
            composable("login") {
                LoginScreen(
                    onLoginClick = {
                        // TODO: Login işlemi yapılacak
                        // Şimdilik direkt giriş yapmış gibi işaretleyelim
                        isLoggedIn = true
                        navController.navigate("map") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    onSignUpClick = {
                        navController.navigate("register")
                    },
                    onForgotPasswordClick = {
                        // TODO: Forgot password screen
                    },
                    onGoogleSignInClick = {
                        // TODO: Google sign in
                    },
                    onAppleSignInClick = {
                        // TODO: Apple sign in
                    }
                )
            }

            // Register Screen
            composable("register") {
                RegisterScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onRegister = {
                        // TODO: Register işlemi yapılacak
                        // Şimdilik direkt giriş yapmış gibi işaretleyelim
                        isLoggedIn = true
                        // Register'da seçilen role'e göre userRole set et
                        // userRole = selectedRole (RegisterScreen'den parametre olarak alınacak)
                        navController.navigate("map") {
                            popUpTo("register") { inclusive = true }
                        }
                    },
                    onLogin = {
                        navController.popBackStack()
                    }
                )
            }

            // ==================== MAIN APP FLOW ====================

            // Map Screen (Ana Ekran)
            composable("map") {
                // TODO: MapScreen implementasyonu
                MapScreenPlaceholder(
                    onSpotClick = { spotId ->
                        navController.navigate("spot_detail/$spotId")
                    }
                )
            }

            // Rezervasyonlarım / Geçmiş
            composable("bookings") {
                // TODO: BookingsScreen implementasyonu
                BookingsScreenPlaceholder()
            }

            // İlan Ekle (Owner only)
            composable("add_listing") {
                if (userRole == UserRole.OWNER) {
                    // TODO: AddListingScreen implementasyonu
                    AddListingScreenPlaceholder()
                }
            }

            // İlanlarım (Owner only)
            composable("listings") {
                if (userRole == UserRole.OWNER) {
                    // TODO: MyListingsScreen implementasyonu
                    MyListingsScreenPlaceholder()
                }
            }

            // Favoriler (Renter)
            composable("favorites") {
                // TODO: FavoritesScreen implementasyonu
                FavoritesScreenPlaceholder()
            }

            // Profil
            composable("profile") {
                ProfileScreen(
                    onNavigateToEditProfile = {
                        navController.navigate("edit_profile")
                    },
                    onNavigateToNotifications = {
                        navController.navigate("notifications")
                    },
                    onNavigateToPaymentMethods = {
                        navController.navigate("payment_methods")
                    },
                    onNavigateToLanguage = {
                        navController.navigate("language")
                    },
                    onNavigateToHelp = {
                        navController.navigate("help")
                    },
                    onLogout = {
                        // Logout işlemi
                        isLoggedIn = false
                        navController.navigate("login") {
                            popUpTo("map") { inclusive = true }
                        }
                    }
                )
            }

            // ==================== DETAIL SCREENS ====================

            // Park Yeri Detay
            composable("spot_detail/{spotId}") { backStackEntry ->
                val spotId = backStackEntry.arguments?.getString("spotId")
                // TODO: SpotDetailScreen implementasyonu
                SpotDetailScreenPlaceholder(
                    spotId = spotId,
                    onBack = { navController.popBackStack() },
                    onReserve = {
                        navController.navigate("create_reservation/$spotId")
                    }
                )
            }

            // Rezervasyon Oluştur
            composable("create_reservation/{spotId}") { backStackEntry ->
                val spotId = backStackEntry.arguments?.getString("spotId")
                // TODO: CreateReservationScreen implementasyonu
                CreateReservationScreenPlaceholder(
                    spotId = spotId,
                    onBack = { navController.popBackStack() }
                )
            }

            // Profil Düzenle
            composable("edit_profile") {
                // TODO: EditProfileScreen implementasyonu
                EditProfileScreenPlaceholder(
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

// ==================== PLACEHOLDER SCREENS ====================
// TODO: Bu ekranları gerçek implementasyonlarla değiştirin

@Composable
fun MapScreenPlaceholder(onSpotClick: (String) -> Unit) {
    androidx.compose.material3.Text("Map Screen - TODO")
}

@Composable
fun BookingsScreenPlaceholder() {
    androidx.compose.material3.Text("Bookings Screen - TODO")
}

@Composable
fun AddListingScreenPlaceholder() {
    androidx.compose.material3.Text("Add Listing Screen - TODO")
}

@Composable
fun MyListingsScreenPlaceholder() {
    androidx.compose.material3.Text("My Listings Screen - TODO")
}

@Composable
fun FavoritesScreenPlaceholder() {
    androidx.compose.material3.Text("Favorites Screen - TODO")
}

@Composable
fun SpotDetailScreenPlaceholder(spotId: String?, onBack: () -> Unit, onReserve: () -> Unit) {
    androidx.compose.material3.Text("Spot Detail Screen - TODO: $spotId")
}

@Composable
fun CreateReservationScreenPlaceholder(spotId: String?, onBack: () -> Unit) {
    androidx.compose.material3.Text("Create Reservation Screen - TODO: $spotId")
}

@Composable
fun EditProfileScreenPlaceholder(onBack: () -> Unit) {
    androidx.compose.material3.Text("Edit Profile Screen - TODO")
}