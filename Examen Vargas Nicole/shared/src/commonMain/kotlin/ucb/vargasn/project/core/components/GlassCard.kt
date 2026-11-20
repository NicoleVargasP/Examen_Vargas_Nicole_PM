package ucb.vargasn.project.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.ThemeManager
import ucb.vargasn.project.theme.AppBrushes
import ucb.vargasn.project.theme.AppTheme

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 24.dp,
    elevation: Dp = 8.dp,
    content: @Composable () -> Unit
) {
    val currentTheme by ThemeManager.currentTheme.collectAsState()
    val shape = RoundedCornerShape(cornerRadius)
    Box(
        modifier = modifier
            .shadow(elevation = elevation, shape = shape, spotColor = AppTheme.colors.primary)
            .clip(shape)
            .background(AppTheme.colors.surface)
            .border(width = 1.dp, brush = AppBrushes.glassBorder(currentTheme), shape = shape)
            .padding(16.dp)
    ) {
        content()
    }
}
