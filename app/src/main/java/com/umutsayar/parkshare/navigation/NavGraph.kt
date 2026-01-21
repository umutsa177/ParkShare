package com.umutsayar.parkshare.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.umutsayar.parkshare.presentation.auth.login.LoginScreen
import com.umutsayar.parkshare.presentation.auth.register.RegisterScreen
import com.umutsayar.parkshare.presentation.main.MainScreen
import com.umutsayar.parkshare.presentation.splash.ParkShareSplashScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        // --- SPLASH ---
        composable(Screens.SplashScreen.route) {
            ParkShareSplashScreen(
                onSplashFinished = {
                    // Splash süresi bitince burası çalışır
                    navController.navigate(Screens.Auth.Login.route) {
                        popUpTo(Screens.SplashScreen.route) { inclusive = true }
                    }
                }
            )
        }

        // --- AUTH GRAPH ---
        navigation(
            startDestination = Screens.Auth.Login.route,
            route = Screens.Auth.Root.route
        ) {
            composable(Screens.Auth.Login.route) {
                LoginScreen(
                    onSignUpClick = { navController.navigate(Screens.Auth.Register.route) },
                    onLoginSuccess = { role ->
                        // Login başarılı olunca rolü MainScreen'e gönder
                        navController.navigate(Screens.Main.Root.createRoute(role)) {
                            popUpTo(Screens.Auth.Root.route) { inclusive = true }
                        }
                    }
                )
            }
            composable(Screens.Auth.Register.route) {
                RegisterScreen(
                    onBack = { navController.popBackStack() },
                    onLogin = { navController.popBackStack() }, // Login'e geri dön
                    onRegisterSuccess = { role ->
                        // Kayıt başarılı olunca seçilen rolü MainScreen'e gönder
                        navController.navigate(Screens.Main.Root.createRoute(role)) {
                            popUpTo(Screens.Auth.Root.route) { inclusive = true }
                        }
                    }
                )
            }
        }

        // --- MAIN SCREEN (BOTTOM NAV) ---
        composable(
            route = Screens.Main.Root.route,
            arguments = listOf(navArgument("userRole") { type = NavType.StringType })
        ) { backStackEntry ->
            // Gelen rolü al, eğer yoksa varsayılan RENTER olsun
            val userRole = backStackEntry.arguments?.getString("userRole") ?: "RENTER"
            MainScreen(userRole = userRole)
        }
    }
}