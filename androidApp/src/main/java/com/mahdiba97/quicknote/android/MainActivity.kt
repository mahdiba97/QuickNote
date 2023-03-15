package com.mahdiba97.quicknote.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mahdiba97.quicknote.android.framwork.presentation.ui.detail.NoteDetailScreen
import com.mahdiba97.quicknote.android.framwork.presentation.ui.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable(route = "home") {
                        HomeScreen(navController = navController)
                    }
                    composable(route = "detail/{nodeId}", arguments = listOf(navArgument("nodeId") {
                        type = NavType.LongType
                        defaultValue = -1L
                    })) { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getLong("nodeId") ?: -1L
                        NoteDetailScreen(noteId = noteId, navController = navController)
                    }
                }
            }
        }
    }
}