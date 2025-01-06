package com.suitmedia.mobile_test.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.suitmedia.mobile_test.presentation.screen.first_screen.FirstScreen
import com.suitmedia.mobile_test.presentation.screen.second_screen.SecondScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "first_screen") {
        composable("first_screen") {
            FirstScreen(navController)
        }
        composable("second_screen") {
            SecondScreen(navController)
        }
        composable("third_screen") {
            SecondScreen(navController)
        }
    }
}
