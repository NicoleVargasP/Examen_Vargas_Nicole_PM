package ucb.vargasn.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ucb.vargasn.project.earthquakes_examen.presentation.screen.EarthquakeScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Earthquake
    ) {

        composable<NavRoute.Earthquake> {
            EarthquakeScreen()
        }
    }
}