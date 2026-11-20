package ucb.vargasn.project.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.theme.AppTheme

@Composable
fun LabScaffold(
    title: String,
    subtitle: String,
    description: String,
    onBack: () -> Unit,
    onReset: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        containerColor = AppTheme.colors.background,
        topBar = {
            Surface(
                color = AppTheme.colors.surface.copy(alpha = 0.95f),
                tonalElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        TextButton(onClick = onBack) {
                            Text("← Volver", color = AppTheme.colors.primary)
                        }
                        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                            Text(
                                text = title,
                                style = AppTheme.typography.heading,
                                color = AppTheme.colors.onBackground,
                                maxLines = 1
                            )
                            Text(
                                text = subtitle,
                                style = AppTheme.typography.label,
                                color = AppTheme.colors.onBackground.copy(alpha = 0.7f),
                                maxLines = 1
                            )
                        }
                    }
                    OutlinedButton(onClick = onReset) {
                        Text("Reiniciar", color = AppTheme.colors.secondary)
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Explanation Info Card
            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                cornerRadius = 20.dp
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Text(
                        text = "💡 ¿Qué demuestra este Lab?",
                        style = AppTheme.typography.label,
                        color = AppTheme.colors.primary
                    )
                    Text(
                        text = description,
                        style = AppTheme.typography.body,
                        color = AppTheme.colors.onBackground.copy(alpha = 0.9f)
                    )
                }
            }

            content()
        }
    }
}
