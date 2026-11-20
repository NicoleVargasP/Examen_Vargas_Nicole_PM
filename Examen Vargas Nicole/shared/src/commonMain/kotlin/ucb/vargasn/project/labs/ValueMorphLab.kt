package ucb.vargasn.project.labs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.core.components.ChipSelector
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.GlowButton
import ucb.vargasn.project.core.components.LabSlider
import ucb.vargasn.project.theme.AccentCyan
import ucb.vargasn.project.theme.AccentMagenta
import ucb.vargasn.project.theme.AppTheme
import ucb.vargasn.project.theme.PrimaryPurple

@Composable
fun ValueMorphLabContent(
    modifier: Modifier = Modifier,
    resetToken: Int = 0
) {
    var animatedMode by remember(resetToken) { mutableStateOf(true) }
    var sizeState by remember(resetToken) { mutableStateOf(110f) }
    var cornerState by remember(resetToken) { mutableStateOf(24f) }
    var rotationState by remember(resetToken) { mutableStateOf(0f) }
    var selectedColor by remember(resetToken) { mutableStateOf(PrimaryPurple) }
    var animDuration by remember(resetToken) { mutableStateOf(600f) }

    val animSize by animateDpAsState(
        targetValue = sizeState.dp,
        animationSpec = if (animatedMode) tween(animDuration.toInt(), easing = FastOutSlowInEasing) else snap(),
        label = "size"
    )
    val animCorner by animateDpAsState(
        targetValue = cornerState.dp,
        animationSpec = if (animatedMode) tween(animDuration.toInt(), easing = FastOutSlowInEasing) else snap(),
        label = "corner"
    )
    val animRotation by animateFloatAsState(
        targetValue = rotationState,
        animationSpec = if (animatedMode) tween(animDuration.toInt(), easing = FastOutSlowInEasing) else snap(),
        label = "rotation"
    )
    val animColor by animateColorAsState(
        targetValue = selectedColor,
        animationSpec = if (animatedMode) tween(animDuration.toInt()) else snap(),
        label = "color"
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GlassCard(
            modifier = Modifier.fillMaxWidth().height(225.dp),
            cornerRadius = 24.dp
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(animSize)
                        .rotate(animRotation)
                        .clip(RoundedCornerShape(animCorner))
                        .background(animColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Morfología",
                        style = AppTheme.typography.label,
                        color = AppTheme.colors.onPrimary
                    )
                }
            }
        }

        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 24.dp
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ChipSelector(
                    items = listOf(true, false),
                    selectedItem = animatedMode,
                    onItemSelected = { animatedMode = it },
                    itemLabel = { if (it) "✨ Con Animación" else "⚡ Sin Animación" }
                )
                ChipSelector(
                    items = listOf(PrimaryPurple, AccentMagenta, AccentCyan),
                    selectedItem = selectedColor,
                    onItemSelected = { selectedColor = it },
                    itemLabel = { if (it == PrimaryPurple) "Púrpura" else if (it == AccentMagenta) "Magenta" else "Cian" }
                )
                LabSlider("Tamaño", sizeState, { sizeState = it; it }, 70f..160f)
                LabSlider("Esquinas", cornerState, { cornerState = it; it }, 0f..80f)
                LabSlider("Rotación", rotationState, { rotationState = it; it }, 0f..360f)
                GlowButton("Aleatorio", onClick = {
                    sizeState = (70..150).random().toFloat()
                    cornerState = (0..60).random().toFloat()
                    rotationState = (0..360).random().toFloat()
                })
            }
        }
    }
}
