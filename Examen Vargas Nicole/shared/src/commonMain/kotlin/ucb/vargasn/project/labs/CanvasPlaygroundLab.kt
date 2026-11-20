package ucb.vargasn.project.labs

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.GlowButton
import ucb.vargasn.project.theme.AppTheme
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun CanvasPlaygroundLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    var playing by remember(resetToken) { mutableStateOf(true) }
    val primaryColor = AppTheme.colors.primary

    val infiniteTransition = rememberInfiniteTransition(label = "wave")
    val phase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "phase"
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
                Canvas(modifier = Modifier.size(280.dp, 160.dp)) {
                    val width = size.width
                    val height = size.height
                    val midY = height / 2

                    val path = Path().apply {
                        moveTo(0f, midY)
                        for (x in 0..width.toInt() step 5) {
                            val angle = (x + (if (playing) phase else 0f)) * (PI / 180.0)
                            val y = midY + (sin(angle) * 40.0).toFloat()
                            lineTo(x.toFloat(), y)
                        }
                    }

                    drawPath(
                        path = path,
                        color = primaryColor,
                        style = Stroke(width = 6f)
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlowButton(if (playing) "Pausar Onda" else "Reanudar Onda", onClick = { playing = !playing })
            }
        }
    }
}
