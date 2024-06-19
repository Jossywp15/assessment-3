package org.d3if3169.doghotel.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if3169.doghotel.ui.screen.AboutScreen
import org.d3if3169.doghotel.ui.screen.FormScreen
import org.d3if3169.doghotel.ui.screen.LandingScreen
import org.d3if3169.doghotel.ui.screen.MainScreen
import org.d3if3169.doghotel.ui.screen.DetailNotesScreen
import org.d3if3169.doghotel.ui.screen.DogPhotosScreen
import org.d3if3169.doghotel.ui.screen.NotesScreen
import org.d3if3169.doghotel.ui.screen.RulesScreen

@Composable
fun SetUpnavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Landing.route
    ) {
        composable(Screen.Landing.route) {
            LandingScreen(navHostController = navController)
        }
        composable(Screen.Main.route) {
            MainScreen(navHostController = navController)
        }
        composable(Screen.About.route) {
            AboutScreen(navHostController = navController)
        }
        composable(Screen.Form.route) {
            FormScreen(navHostController = navController)
        }
        composable(Screen.Rules.route) {
            RulesScreen(navHostController = navController)
        }
        composable(Screen.Notes.route) {
            NotesScreen(navHostController = navController)
        }
        composable(Screen.DogPhotos.route) {
            DogPhotosScreen(navHostController = navController)
        }
        composable(Screen.AddNotes.route) {
            DetailNotesScreen(navHostController = navController)
        }
        composable(Screen.EditNotes.route,
            arguments = listOf(
                navArgument(KEY_NOTES_ID) {
                    type = NavType.IntType
                }
            )) {
            val id = it.arguments?.getInt(KEY_NOTES_ID)
            DetailNotesScreen(navHostController = navController, id)
        }
    }
}
