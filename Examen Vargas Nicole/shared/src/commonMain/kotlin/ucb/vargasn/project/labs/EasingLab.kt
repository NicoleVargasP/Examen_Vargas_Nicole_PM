package ucb.vargasn.project.labs

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.core.components.ChipSelector
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.GlowButton
import ucb.vargasn.project.core.components.LabSlider
import ucb.vargasn.project.theme.PrimaryLight
import ucb.vargasn.project.theme.PrimaryPurple

@Composable
fun EasingLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    var easingName by remember(resetToken) { mutableStateOf("FastOutSlowIn") }
    var duration by remember(resetToken) { mutableStateOf(1000f) }
    var isPlaying by remember(resetToken) { mutableStateOf(false) }

    val easing = when (easingName) {
        "Linear" -> LinearEasing
        "EaseIn" -> FastOutLinearInEasing
        "EaseOut" -> LinearOutSlowInEasing
        else -> FastOutSlowInEasing
    }

    val infiniteTransition = rememberInfiniteTransition(label = "easingLoop")
    val progress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (isPlaying) 1f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(duration.toInt(), easing = easing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "progress"
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
                Canvas(modifier = Modifier.size(220.dp)) {
                    val width = size.width
                    val height = size.height

                    val path = Path().apply {
                        moveTo(0f, height)
                        cubicTo(width * 0.4f, height * 0.9f, width * 0.6f, height * 0.1f, width, 0f)
                    }
                    drawPath(path, color = PrimaryLight.copy(alpha = 0.4f), style = Stroke(width = 4f))

                    val dotX = width * progress
                    val dotY = height * (1f - progress)
                    drawCircle(color = PrimaryPurple, radius = 12f, center = Offset(dotX, dotY))
                    drawCircle(color = Color.White, radius = 6f, center = Offset(dotX, dotY))
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
                    items = listOf("FastOutSlowIn", "Linear", "EaseIn", "EaseOut"),
                    selectedItem = easingName,
                    onItemSelected = { easingName = it },
                    itemLabel = { it }
                )
                LabSlider("Duración (${duration.toInt()}ms)", duration, { duration = it; it }, 400f..3000f)
                GlowButton(if (isPlaying) "Pausar" else "Reproducir", onClick = { isPlaying = !isPlaying })
            }
        }
    }
}
