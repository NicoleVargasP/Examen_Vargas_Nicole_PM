package ucb.vargasn.project.labs

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.GlowButton
import ucb.vargasn.project.theme.AppTheme

@Composable
fun TextMotionLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    var targetValue by remember(resetToken) { mutableStateOf(42) }

    val animatedValue by animateIntAsState(
        targetValue = targetValue,
        animationSpec = tween(1000, easing = FastOutSlowInEasing),
        label = "counter"
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
                Text(
                    text = "$animatedValue",
                    style = AppTheme.typography.heading.copy(
                        fontSize = 72.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = AppTheme.colors.primary
                )
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
                GlowButton("Cambiar Número", onClick = {
                    targetValue = (10..999).random()
                })
            }
        }
    }
}
