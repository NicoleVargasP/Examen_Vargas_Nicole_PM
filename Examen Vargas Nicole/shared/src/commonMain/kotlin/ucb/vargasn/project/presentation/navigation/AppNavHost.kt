package ucb.vargasn.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ucb.vargasn.project.catalog.presentation.screen.CatalogScreen

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Catalog
    ) {

        composable<NavRoute.Catalog> {
            CatalogScreen()
        }

    }
}