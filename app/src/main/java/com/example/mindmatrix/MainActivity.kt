package com.example.mindmatrix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mindmatrix.ui.screens.DetailScreen
import com.example.mindmatrix.ui.screens.HospitalFinderScreen
import com.example.mindmatrix.ui.screens.MainScreen
import com.example.mindmatrix.ui.theme.MindmatrixTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindmatrixTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                onEmergencyClick = { id ->
                    navController.navigate("detail/$id")
                },
                onHospitalFinderClick = {
                    navController.navigate("hospitals")
                }
            )
        }
        composable(
            "detail/{emergencyId}",
            arguments = listOf(navArgument("emergencyId") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("emergencyId") ?: 0
            DetailScreen(
                emergencyId = id,
                onBack = { navController.popBackStack() }
            )
        }
        composable("hospitals") {
            HospitalFinderScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
