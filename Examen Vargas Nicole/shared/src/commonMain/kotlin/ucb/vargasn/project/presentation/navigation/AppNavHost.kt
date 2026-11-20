package ucb.vargasn.project.presentation.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ucb.vargasn.project.core.components.LabScaffold
import ucb.vargasn.project.core.model.LabRegistry
import ucb.vargasn.project.presentation.screen.HomeScreen
import ucb.vargasn.project.presentation.screen.WelcomeScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Welcome
    ) {
        composable<NavRoute.Welcome> {
            WelcomeScreen(
                onGetStarted = {
                    navController.navigate(NavRoute.Home) {
                        popUpTo(NavRoute.Welcome) { inclusive = true }
                    }
                }
            )
        }

        composable<NavRoute.Home> {
            HomeScreen(
                onSelectLab = { labId ->
                    navController.navigate(NavRoute.LabDetail(labId))
                }
            )
        }

        composable<NavRoute.LabDetail> { backStackEntry ->
            val route = backStackEntry.toRoute<NavRoute.LabDetail>()
            val lab = LabRegistry.labs.find { route.labId == it.id } ?: LabRegistry.labs.first()
            var resetToken by remember { mutableStateOf(0) }

            LabScaffold(
                title = lab.title,
                subtitle = lab.subtitle,
                description = lab.description,
                onBack = { navController.popBackStack() },
                onReset = { resetToken++ },
                content = {
                    lab.content(androidx.compose.ui.Modifier, resetToken)
                }
            )
        }
    }
}
