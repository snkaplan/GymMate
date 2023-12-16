package com.gym.gymmate.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.gym.gymmate.feature.home.HomeNavigationRoute
import com.gym.gymmate.feature.home.homeScreen
import com.gym.gymmate.feature.home.navigateToHome
import com.gym.gymmate.feature.login.LoginNavigationRoute
import com.gym.gymmate.feature.login.loginScreen
import com.gym.gymmate.feature.login.navigateLogin
import com.gym.gymmate.feature.splash.SplashNavigationRoute
import com.gym.gymmate.feature.splash.splashScreen

@Composable
fun GymMateNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = SplashNavigationRoute
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        splashScreen(
            navigateToLogin = {
                navController.navigateLogin(
                    navOptions = navOptions {
                        popUpTo(SplashNavigationRoute) {
                            inclusive = true
                        }
                    },
                )
            },
            navigateToHome = {
                navController.navigateToHome(
                    navOptions = navOptions {
                        popUpTo(SplashNavigationRoute) {
                            inclusive = true
                        }
                    },
                )
            }
        )
        homeScreen()
        loginScreen(navigateToHome = {
            navController.navigateToHome(
                navOptions = navOptions {
                    popUpTo(LoginNavigationRoute) {
                        inclusive = true
                    }
                },
            )
        })
    }
}