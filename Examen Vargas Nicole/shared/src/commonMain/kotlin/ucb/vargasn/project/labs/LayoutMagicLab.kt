package ucb.vargasn.project.labs

import androidx.compose.animation.animateContentSize
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
fun LayoutMagicLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    var expanded by remember(resetToken) { mutableStateOf(false) }

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
                        .fillMaxWidth(0.85f)
                        .animateContentSize()
                        .clip(RoundedCornerShape(24.dp))
                        .background(AppTheme.colors.primary)
                        .clickable { expanded = !expanded }
                        .padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = if (expanded) "Contenido Expandido" else "Toca para Expandir",
                            style = AppTheme.typography.heading,
                            color = AppTheme.colors.onPrimary
                        )
                        if (expanded) {
                            Text(
                                text = "animateContentSize ajusta automáticamente el layout con una animación fluida al cambiar el tamaño del contenido.",
                                style = AppTheme.typography.body,
                                color = AppTheme.colors.onPrimary.copy(alpha = 0.9f)
                            )
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
                GlowButton(if (expanded) "Contraer" else "Expandir", onClick = { expanded = !expanded })
            }
        }
    }
}
