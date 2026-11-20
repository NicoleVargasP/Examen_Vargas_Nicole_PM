package ucb.vargasn.project.labs

import androidx.compose.animation.animateColor
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
import ucb.vargasn.project.theme.AccentMagenta
import ucb.vargasn.project.theme.AppTheme
import ucb.vargasn.project.theme.PrimaryPurple

@Composable
fun TransitionStudioLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    var stateName by remember(resetToken) { mutableStateOf("Reposo") }

    val transition = updateTransition(targetState = stateName, label = "studioTransition")

    val color by transition.animateColor(label = "color") { state ->
        when (state) {
            "Hover" -> AccentMagenta
            "Seleccionado" -> AppTheme.colors.secondary
            else -> PrimaryPurple
        }
    }

    val size by transition.animateDp(label = "size") { state ->
        when (state) {
            "Hover" -> 140.dp
            "Seleccionado" -> 160.dp
            else -> 100.dp
        }
    }

    val rotation by transition.animateFloat(label = "rotation") { state ->
        when (state) {
            "Hover" -> 15f
            "Seleccionado" -> 45f
            else -> 0f
        }
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
                Box(
                    modifier = Modifier
                        .size(size)
                        .rotate(rotation)
                        .clip(RoundedCornerShape(24.dp))
                        .background(color),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stateName,
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
                    items = listOf("Reposo", "Hover", "Seleccionado"),
                    selectedItem = stateName,
                    onItemSelected = { stateName = it },
                    itemLabel = { it }
                )
            }
        }
    }
}
