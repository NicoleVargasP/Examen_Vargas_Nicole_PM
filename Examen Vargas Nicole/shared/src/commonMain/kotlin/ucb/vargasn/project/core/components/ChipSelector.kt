package ucb.vargasn.project.core.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.theme.AppTheme

@Composable
fun <T> ChipSelector(
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit,
    itemLabel: (T) -> String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { item ->
            val selected = item == selectedItem
            FilterChip(
                selected = selected,
                onClick = { onItemSelected(item) },
                label = { Text(itemLabel(item)) },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = AppTheme.colors.primary,
                    selectedLabelColor = AppTheme.colors.onPrimary,
                    containerColor = AppTheme.colors.surface,
                    labelColor = AppTheme.colors.onSurface
                )
            )
        }
    }
}
