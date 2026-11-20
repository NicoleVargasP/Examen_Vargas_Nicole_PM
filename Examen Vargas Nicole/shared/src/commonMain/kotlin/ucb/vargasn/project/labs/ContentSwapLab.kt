package ucb.vargasn.project.labs

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.core.components.ChipSelector
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.GlowButton
import ucb.vargasn.project.theme.AppTheme

@Composable
fun ContentSwapLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    var count by remember(resetToken) { mutableStateOf(1) }
    var swapStyle by remember(resetToken) { mutableStateOf("Deslizamiento") }

    val transitionSpec = when (swapStyle) {
        "Deslizamiento" -> slideInVertically { it } togetherWith slideOutVertically { -it }
        "Escala" -> scaleIn() togetherWith scaleOut()
        else -> fadeIn() togetherWith fadeOut()
    }

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
                    targetState = count,
                    transitionSpec = { transitionSpec },
                    label = "contentSwap"
                ) { targetCount ->
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(AppTheme.colors.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Elemento #$targetCount",
                            style = AppTheme.typography.heading,
                            color = AppTheme.colors.onPrimary
                        )
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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ChipSelector(
                    items = listOf("Deslizamiento", "Escala", "Desvanecimiento"),
                    selectedItem = swapStyle,
                    onItemSelected = { swapStyle = it },
                    itemLabel = { it }
                )
                GlowButton("Cambiar Contenido", onClick = { count++ })
            }
        }
    }
}
