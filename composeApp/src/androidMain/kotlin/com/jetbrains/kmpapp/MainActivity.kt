package com.jetbrains.kmpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jetbrains.kmpapp.screens.DetailScreen
import com.jetbrains.kmpapp.screens.ListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "list") {
                composable("list") {
                    ListScreen(navigateToDetails = { objectName ->
                        navController.navigate("details/$objectName")
                    })
                }
                composable(
                    "details/{objectName}",
                    arguments = listOf(navArgument("objectName") { type = NavType.StringType })
                ) { backstack ->
                    DetailScreen(
                        objectName = backstack.arguments?.getString("objectName")!!,
                        navigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
