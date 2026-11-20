package ucb.vargasn.project.labs

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.GlowButton
import ucb.vargasn.project.theme.AppTheme

@Composable
fun SharedElementsLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    var isDetail by remember(resetToken) { mutableStateOf(false) }

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
                AnimatedContent(
                    targetState = isDetail,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "sharedTransition"
                ) { detail ->
                    if (!detail) {
                        Box(
                            modifier = Modifier
                                .size(140.dp)
                                .clip(RoundedCornerShape(20.dp))
                                .background(AppTheme.colors.primary)
                                .clickable { isDetail = true },
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Tarjeta", style = AppTheme.typography.heading, color = AppTheme.colors.onPrimary)
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .height(180.dp)
                                .clip(RoundedCornerShape(28.dp))
                                .background(AppTheme.colors.secondary)
                                .clickable { isDetail = false }
                                .padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text("Vista Detallada", style = AppTheme.typography.heading, color = AppTheme.colors.onSecondary)
                                Text("Toca para regresar", style = AppTheme.typography.label, color = AppTheme.colors.onSecondary.copy(alpha = 0.8f))
                            }
                        }
                    }
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
                GlowButton(if (isDetail) "Ver Tarjeta" else "Ver Detalle", onClick = { isDetail = !isDetail })
            }
        }
    }
}
