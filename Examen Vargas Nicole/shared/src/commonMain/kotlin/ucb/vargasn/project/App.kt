package ucb.vargasn.project

import androidx.compose.runtime.*
import kotlinx.coroutines.flow.MutableStateFlow
import ucb.vargasn.project.presentation.navigation.AppNavHost
import ucb.vargasn.project.theme.AppDesignSystemTheme

object ThemeManager {
    val currentTheme = MutableStateFlow("Claro")
}

@Composable
fun App() {
    val themeMode by ThemeManager.currentTheme.collectAsState()

    AppDesignSystemTheme(themeMode = themeMode) {
        AppNavHost()
    }
}
