package ucb.vargasn.project.labs

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ucb.vargasn.project.core.components.ChipSelector
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.GlowButton
import ucb.vargasn.project.theme.AppTheme

@Composable
fun SequencingStaggerLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    var mode by remember(resetToken) { mutableStateOf("Stagger") }
    var trigger by remember(resetToken) { mutableStateOf(false) }

    val barCount = 5
    val anims = remember(resetToken) { List(barCount) { Animatable(30f) } }

    LaunchedEffect(resetToken) {
        trigger = false
        anims.forEach { it.snapTo(30f) }
    }

    LaunchedEffect(trigger) {
        if (trigger) {
            when (mode) {
                "Secuencial" -> {
                    for (i in anims.indices) {
                        anims[i].animateTo(140f, tween(300))
                    }
                }
                "Paralelo" -> {
                    launch {
                        anims.forEach { it.animateTo(140f, tween(500)) }
                    }
                }
                else -> { // Stagger
                    for (i in anims.indices) {
                        launch {
                            delay((i * 100).toLong())
                            anims[i].animateTo(140f, tween(400))
                        }
                    }
                }
            }
        } else {
            anims.forEach { it.animateTo(30f, tween(300)) }
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
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.height(160.dp)
                ) {
                    anims.forEachIndexed { index, anim ->
                        Box(
                            modifier = Modifier
                                .width(28.dp)
                                .height(anim.value.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(AppTheme.colors.primary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("${index + 1}", style = AppTheme.typography.label, color = AppTheme.colors.onPrimary)
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
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChipSelector(
                    items = listOf("Stagger", "Secuencial", "Paralelo"),
                    selectedItem = mode,
                    onItemSelected = { mode = it },
                    itemLabel = { it }
                )
                GlowButton(if (trigger) "Reiniciar" else "Animar Barras", onClick = { trigger = !trigger })
            }
        }
    }
}
