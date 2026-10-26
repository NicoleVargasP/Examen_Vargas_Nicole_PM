package ucb.vargasn.project.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalAppColors = staticCompositionLocalOf { LightColors }
val LocalAppTypography = staticCompositionLocalOf { DefaultTypography }
val LocalAppSpacing = staticCompositionLocalOf { DefaultSpacing }

object AppTheme {
    val colors: AppColors
        @Composable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        get() = LocalAppTypography.current

    val spacing: AppSpacing
        @Composable
        get() = LocalAppSpacing.current
}

@Composable
fun AppDesignSystemTheme(
    themeMode: String = "Claro",
    content: @Composable () -> Unit
) {
    val appColors = when (themeMode) {
        "Oscuro" -> DarkColors
        "Alto Contraste" -> HighContrastColors
        else -> LightColors
    }

    val materialColorScheme = when (themeMode) {
        "Oscuro" -> darkColorScheme(
            primary = DarkColors.primary,
            secondary = DarkColors.secondary,
            background = DarkColors.background,
            surface = DarkColors.surface,
            error = DarkColors.error
        )
        "Alto Contraste" -> darkColorScheme(
            primary = HighContrastColors.primary,
            secondary = HighContrastColors.secondary,
            background = HighContrastColors.background,
            surface = HighContrastColors.surface,
            error = HighContrastColors.error
        )
        else -> lightColorScheme(
            primary = LightColors.primary,
            secondary = LightColors.secondary,
            background = LightColors.background,
            surface = LightColors.surface,
            error = LightColors.error
        )
    }

    CompositionLocalProvider(
        LocalAppColors provides appColors,
        LocalAppTypography provides DefaultTypography,
        LocalAppSpacing provides DefaultSpacing
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            content = content
        )
    }
}
