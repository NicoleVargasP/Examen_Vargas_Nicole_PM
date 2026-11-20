package ucb.vargasn.project.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object AppBrushes {
    fun primaryGradient(
        start: Offset = Offset.Zero,
        end: Offset = Offset.Infinite
    ): Brush = Brush.linearGradient(
        colors = listOf(PrimaryPurple, AccentMagenta),
        start = start,
        end = end
    )

    fun auroraGradient(
        color1: Color = PrimaryPurple,
        color2: Color = AccentCyan,
        color3: Color = AccentMagenta
    ): Brush = Brush.radialGradient(
        colors = listOf(color1, color2, color3, BgDeep),
        radius = 1200f
    )

    fun glassBorder(themeMode: String = "Oscuro"): Brush = when (themeMode) {
        "Claro" -> Brush.linearGradient(
            colors = listOf(
                Color(0xFFCBD5E1),
                Color(0xFF94A3B8)
            )
        )
        "Alto Contraste" -> Brush.linearGradient(
            colors = listOf(
                Color.Yellow,
                Color.Cyan
            )
        )
        else -> Brush.linearGradient(
            colors = listOf(
                PrimaryLight.copy(alpha = 0.6f),
                PrimaryPurple.copy(alpha = 0.1f),
                AccentMagenta.copy(alpha = 0.4f)
            )
        )
    }

    fun cardBackground(themeMode: String = "Oscuro"): Brush = when (themeMode) {
        "Claro" -> Brush.verticalGradient(
            colors = listOf(
                LightSurface,
                LightSurfaceVariant
            )
        )
        "Alto Contraste" -> Brush.verticalGradient(
            colors = listOf(
                Color.Black,
                Color.Black
            )
        )
        else -> Brush.verticalGradient(
            colors = listOf(
                SurfaceColor.copy(alpha = 0.85f),
                SurfaceVariantColor.copy(alpha = 0.6f)
            )
        )
    }
}
