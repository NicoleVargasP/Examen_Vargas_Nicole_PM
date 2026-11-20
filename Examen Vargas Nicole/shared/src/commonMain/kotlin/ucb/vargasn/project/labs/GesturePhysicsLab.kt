package ucb.vargasn.project.labs

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.GlowButton
import ucb.vargasn.project.theme.AppTheme
import kotlin.math.roundToInt

@Composable
fun GesturePhysicsLabContent(modifier: Modifier = Modifier, resetToken: Int = 0) {
    val coroutineScope = rememberCoroutineScope()
    val offsetX = remember(resetToken) { Animatable(0f) }
    val offsetY = remember(resetToken) { Animatable(0f) }

    LaunchedEffect(resetToken) {
        offsetX.snapTo(0f)
        offsetY.snapTo(0f)
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
                        .offset { IntOffset(offsetX.value.roundToInt(), offsetY.value.roundToInt()) }
                        .size(110.dp)
                        .clip(CircleShape)
                        .background(AppTheme.colors.primary)
                        .pointerInput(resetToken) {
                            detectDragGestures(
                                onDragEnd = {
                                    coroutineScope.launch {
                                        launch { offsetX.animateTo(0f, spring(dampingRatio = 0.4f, stiffness = 300f)) }
                                        launch { offsetY.animateTo(0f, spring(dampingRatio = 0.4f, stiffness = 300f)) }
                                    }
                                },
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    coroutineScope.launch {
                                        offsetX.snapTo(offsetX.value + dragAmount.x)
                                        offsetY.snapTo(offsetY.value + dragAmount.y)
                                    }
                                }
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Arrástrame",
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlowButton("Centrar", onClick = {
                    coroutineScope.launch {
                        offsetX.animateTo(0f)
                        offsetY.animateTo(0f)
                    }
                })
            }
        }
    }
}
