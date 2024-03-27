package mikhan.nik.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mikhan.nik.about_screen.AboutScreen
import mikhan.nik.note_list_screen.NoteListScreen
import mikhan.nik.settings_screen.SettingsScreen
import mikhan.nik.shopping_list_screen.ShoppingListScreen
import mikhan.nik.utils.Routes

@Composable
fun NavigationGraph(navController: NavHostController, onNavigate: (String) -> Unit) {
    NavHost(navController = navController, startDestination = Routes.BUY_LIST) {
        composable(Routes.BUY_LIST) {
            ShoppingListScreen() {route ->
                onNavigate(route)
            }
        }
        composable(Routes.NOTE_LIST) {
            NoteListScreen() {  route ->
                onNavigate(route)
            }
        }
        composable(Routes.ABOUT) {
            AboutScreen()
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
    }
}