package ucb.vargasn.project.labs

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.core.components.ChipSelector
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.LabSlider
import ucb.vargasn.project.theme.AppTheme

@Composable
fun InfiniteLoopsLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    var loopType by remember(resetToken) { mutableStateOf("Pulso") }
    var speed by remember(resetToken) { mutableStateOf(1f) }

    val infiniteTransition = rememberInfiniteTransition(label = "infinite")

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween((1000 / speed).toInt(), easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
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
                        .size(140.dp)
                        .scale(if (loopType == "Pulso") scale else 1f)
                        .clip(if (loopType == "Pulso") CircleShape else RoundedCornerShape(24.dp))
                        .background(AppTheme.colors.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(loopType, color = AppTheme.colors.onPrimary)
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
                    items = listOf("Pulso", "Blob"),
                    selectedItem = loopType,
                    onItemSelected = { loopType = it },
                    itemLabel = { it }
                )
                LabSlider("Velocidad (${speed}x)", speed, { speed = it; it }, 0.5f..3f)
            }
        }
    }
}
