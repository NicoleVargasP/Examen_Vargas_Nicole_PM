package ucb.vargasn.project.theme

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

val LightColors = AppColors(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFFFFFFFF),
    error = Color(0xFFB00020),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onError = Color.White,
    onSurface = Color.Black
)

val DarkColors = AppColors(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFF121212),
    error = Color(0xFFCF6679),
    surface = Color(0xFF121212),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onError = Color.Black,
    onSurface = Color.White
)

val HighContrastColors = AppColors(
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
