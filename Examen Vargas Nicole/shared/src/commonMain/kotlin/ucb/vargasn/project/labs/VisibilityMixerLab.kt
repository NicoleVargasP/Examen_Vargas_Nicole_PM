package ucb.vargasn.project.labs

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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
fun VisibilityMixerLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    var visible by remember(resetToken) { mutableStateOf(true) }
    var animStyle by remember(resetToken) { mutableStateOf("Fade + Scale") }

    val enterTransition = when (animStyle) {
        "Fade" -> fadeIn(animationSpec = tween(500))
        "Slide" -> slideInVertically(animationSpec = tween(500)) { it }
        "Scale" -> scaleIn(animationSpec = tween(500))
        else -> fadeIn(animationSpec = tween(500)) + scaleIn(animationSpec = tween(500))
    }

    val exitTransition = when (animStyle) {
        "Fade" -> fadeOut(animationSpec = tween(500))
        "Slide" -> slideOutVertically(animationSpec = tween(500)) { it }
        "Scale" -> scaleOut(animationSpec = tween(500))
        else -> fadeOut(animationSpec = tween(500)) + scaleOut(animationSpec = tween(500))
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
                androidx.compose.animation.AnimatedVisibility(
                    visible = visible,
                    enter = enterTransition,
                    exit = exitTransition
                ) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(AppTheme.colors.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("¡Hola Visibilidad!", color = AppTheme.colors.onPrimary)
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
                    items = listOf("Fade + Scale", "Fade", "Slide", "Scale"),
                    selectedItem = animStyle,
                    onItemSelected = { animStyle = it },
                    itemLabel = { it }
                )
                GlowButton(if (visible) "Ocultar" else "Mostrar", onClick = { visible = !visible })
            }
        }
    }
}
