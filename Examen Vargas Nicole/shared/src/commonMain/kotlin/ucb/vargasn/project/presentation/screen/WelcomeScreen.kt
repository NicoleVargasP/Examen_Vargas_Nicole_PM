package ucb.vargasn.project.presentation.screen

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ucb.vargasn.project.ThemeManager
import ucb.vargasn.project.core.components.AuroraBackground
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.components.GlowButton
import ucb.vargasn.project.theme.AccentMagenta
import ucb.vargasn.project.theme.AppTheme
import ucb.vargasn.project.theme.PrimaryPurple

@Composable
fun WelcomeScreen(
    onGetStarted: () -> Unit
) {
    val currentTheme by ThemeManager.currentTheme.collectAsState()

    val infiniteTransition = rememberInfiniteTransition(label = "welcomePulse")
    val logoScale by infiniteTransition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logoScale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        if (currentTheme == "Oscuro") {
            AuroraBackground()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Center Animated Hero
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Pulsing glowing logo container
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .scale(logoScale)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(PrimaryPurple, AccentMagenta, AppTheme.colors.secondary)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .background(AppTheme.colors.surface),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "✨",
                            style = AppTheme.typography.heading.copy(fontSize = 48.sp)
                        )
                    }
                }

                Text(
                    text = "Prism Motion",
                    style = AppTheme.typography.heading.copy(fontSize = 36.sp),
                    color = AppTheme.colors.onBackground
                )

                Text(
                    text = "Descubre, experimenta y domina las animaciones de Compose en un entorno interactivo y visual.",
                    style = AppTheme.typography.body,
                    color = AppTheme.colors.onBackground.copy(alpha = 0.8f),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            // Bottom Action Card & Button
            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                cornerRadius = 28.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        FeatureBadge("14 Labs", "Animaciones nativas")
                        FeatureBadge("E-Commerce", "Caso real")
                        FeatureBadge("3 Temas", "Accesibilidad")
                    }

                    GlowButton(
                        text = "Comenzar Experiencia →",
                        onClick = onGetStarted
                    )
                }
            }
        }
    }
}

@Composable
private fun FeatureBadge(title: String, subtitle: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = title,
            style = AppTheme.typography.heading.copy(fontSize = 16.sp),
            color = AppTheme.colors.primary
        )
        Text(
            text = subtitle,
            style = AppTheme.typography.label.copy(fontSize = 12.sp),
            color = AppTheme.colors.onBackground.copy(alpha = 0.7f)
        )
    }
}
