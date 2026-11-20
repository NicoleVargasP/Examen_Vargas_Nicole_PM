package ucb.vargasn.project.core.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.theme.AppTheme

@Composable
fun LabSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Float,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int = 0,
    valueFormatter: (Float) -> String = { it.toString() }
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = AppTheme.typography.body,
                color = AppTheme.colors.onBackground
            )
            Text(
                text = valueFormatter(value),
                style = AppTheme.typography.body,
                color = AppTheme.colors.primary
            )
        }
        Slider(
            value = value,
            onValueChange = { newVal -> onValueChange(newVal) },
            valueRange = valueRange,
            steps = steps,
            colors = SliderDefaults.colors(
                thumbColor = AppTheme.colors.primary,
                activeTrackColor = AppTheme.colors.primary,
                inactiveTrackColor = AppTheme.colors.surface
            ),
            modifier = Modifier.height(36.dp)
        )
    }
}
