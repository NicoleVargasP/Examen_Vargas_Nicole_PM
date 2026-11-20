package ucb.vargasn.project.labs

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.GlowButton
import ucb.vargasn.project.core.components.LabSlider
import ucb.vargasn.project.theme.AppTheme

@Composable
fun SpringPlaygroundLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    var damping by remember(resetToken) { mutableStateOf(0.4f) }
    var stiffness by remember(resetToken) { mutableStateOf(300f) }
    var trigger by remember(resetToken) { mutableStateOf(false) }

    val offsetAnim by animateFloatAsState(
        targetValue = if (trigger) 130f else -130f,
        animationSpec = spring(
            dampingRatio = damping,
            stiffness = stiffness
        ),
        label = "springBall"
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
                        .offset(x = offsetAnim.dp)
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(AppTheme.colors.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Resorte",
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
                LabSlider("Amortiguación (${damping})", damping, { damping = it; it }, 0.1f..1.5f)
                LabSlider("Rigidez (${stiffness})", stiffness, { stiffness = it; it }, 50f..3000f)
                GlowButton("Soltar / Rebotar", onClick = { trigger = !trigger })
            }
        }
    }
}
