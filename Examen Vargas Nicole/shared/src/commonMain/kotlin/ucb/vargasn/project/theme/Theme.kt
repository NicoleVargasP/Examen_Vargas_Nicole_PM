package ucb.vargasn.project.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class AppColors(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val error: Color,
    val surface: Color,
    val onPrimary: Color,
    val onSecondary: Color,
    val onBackground: Color,
    val onError: Color,
    val onSurface: Color
)

val PrismDarkColors = AppColors(
    primary = PrimaryPurple,
    secondary = AccentCyan,
    background = BgDeep,
    error = ErrorColor,
    surface = SurfaceColor,
    onPrimary = OnPrimary,
    onSecondary = Color.Black,
    onBackground = OnBackground,
    onError = OnPrimary,
    onSurface = OnSurface
)

val PrismLightColors = AppColors(
    primary = LightPrimary,
    secondary = AccentCyan,
    background = LightBg,
    error = Color(0xFFDC2626),
    surface = LightSurface,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = LightOnBackground,
    onError = Color.White,
    onSurface = LightOnSurface
)

val PrismHighContrastColors = AppColors(
    primary = Color.Yellow,
    secondary = Color.Cyan,
    background = Color.Black,
    error = Color.Red,
    surface = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.Yellow,
    onError = Color.Black,
    onSurface = Color.Yellow
)

val LocalAppColors = staticCompositionLocalOf { PrismDarkColors }
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
    themeMode: String = "Oscuro",
    content: @Composable () -> Unit
) {
    val appColors = when (themeMode) {
        "Claro" -> PrismLightColors
        "Alto Contraste" -> PrismHighContrastColors
        else -> PrismDarkColors
    }

    val materialColorScheme = when (themeMode) {
        "Claro" -> lightColorScheme(
            primary = PrismLightColors.primary,
            secondary = PrismLightColors.secondary,
            background = PrismLightColors.background,
            surface = PrismLightColors.surface,
            onBackground = PrismLightColors.onBackground,
            onSurface = PrismLightColors.onSurface,
            error = PrismLightColors.error
        )
        "Alto Contraste" -> darkColorScheme(
            primary = PrismHighContrastColors.primary,
            secondary = PrismHighContrastColors.secondary,
            background = PrismHighContrastColors.background,
            surface = PrismHighContrastColors.surface,
            onBackground = PrismHighContrastColors.onBackground,
            onSurface = PrismHighContrastColors.onSurface,
            error = PrismHighContrastColors.error
        )
        else -> darkColorScheme(
            primary = PrismDarkColors.primary,
            secondary = PrismDarkColors.secondary,
            background = PrismDarkColors.background,
            surface = PrismDarkColors.surface,
            onBackground = PrismDarkColors.onBackground,
            onSurface = PrismDarkColors.onSurface,
            error = PrismDarkColors.error
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
