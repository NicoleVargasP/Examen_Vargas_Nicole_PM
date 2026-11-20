package ucb.vargasn.project.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.ThemeManager
import ucb.vargasn.project.core.components.ChipSelector
import ucb.vargasn.project.core.components.LabSlider
import ucb.vargasn.project.core.components.ToggleRow
import ucb.vargasn.project.core.model.MotionSettingsState
import ucb.vargasn.project.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsBottomSheet(
    motionSettings: MotionSettingsState,
    onDismiss: () -> Unit
) {
    val currentTheme by ThemeManager.currentTheme.collectAsState()

    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Configuración de Animación",
                style = AppTheme.typography.heading,
                color = AppTheme.colors.onBackground
            )

            // Theme Mode
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Tema Visual",
                    style = AppTheme.typography.body,
                    color = AppTheme.colors.onBackground
                )
                ChipSelector(
                    items = listOf("Oscuro", "Claro", "Alto Contraste"),
                    selectedItem = currentTheme,
                    onItemSelected = { ThemeManager.currentTheme.value = it },
                    itemLabel = { it }
                )
            }

            // Speed Multiplier
            LabSlider(
                label = "Multiplicador de Velocidad",
                value = motionSettings.speedMultiplier,
                onValueChange = { motionSettings.speedMultiplier = it; it },
                valueRange = 0.25f..2.0f,
                steps = 6,
                valueFormatter = { "${((it * 100).toInt())}%" }
            )

            // Reduce Motion
            ToggleRow(
                title = "Reducir Movimiento",
                checked = motionSettings.reduceMotion,
                onCheckedChange = { motionSettings.reduceMotion = it }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
