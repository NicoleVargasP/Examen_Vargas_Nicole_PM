package ucb.vargasn.project.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.ThemeManager
import ucb.vargasn.project.core.components.AuroraBackground
import ucb.vargasn.project.core.components.ChipSelector
import ucb.vargasn.project.core.components.GlassCard
import ucb.vargasn.project.core.model.Lab
import ucb.vargasn.project.core.model.LabCategory
import ucb.vargasn.project.core.model.LabRegistry
import ucb.vargasn.project.core.model.LocalMotionSettings
import ucb.vargasn.project.theme.AppTheme

@Composable
fun HomeScreen(
    onSelectLab: (String) -> Unit
) {
    var selectedCategory by remember { mutableStateOf<LabCategory?>(null) }
    var showSettings by remember { mutableStateOf(false) }
    val motionSettings = LocalMotionSettings.current
    val currentTheme by ThemeManager.currentTheme.collectAsState()

    val filteredLabs = remember(selectedCategory) {
        if (selectedCategory == null) LabRegistry.labs
        else LabRegistry.labs.filter { it.category == selectedCategory }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        if (currentTheme == "Oscuro") {
            AuroraBackground()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Professional Top Header Card
            GlassCard(
                modifier = Modifier.fillMaxWidth(),
                cornerRadius = 24.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Prism Motion",
                            style = AppTheme.typography.heading,
                            color = AppTheme.colors.onBackground
                        )
                        Text(
                            text = "Galería Interactiva de Animaciones",
                            style = AppTheme.typography.label,
                            color = AppTheme.colors.onBackground.copy(alpha = 0.75f)
                        )
                    }
                    IconButton(
                        onClick = { showSettings = true },
                        modifier = Modifier.size(48.dp)
                    ) {
                        Text("⚙️", style = AppTheme.typography.heading)
                    }
                }
            }

            // Category Chips
            Box(modifier = Modifier.fillMaxWidth()) {
                val categories = listOf(null) + LabCategory.entries.toList()
                ChipSelector(
                    items = categories,
                    selectedItem = selectedCategory,
                    onItemSelected = { selectedCategory = it },
                    itemLabel = { it?.title ?: "Todos" }
                )
            }

            // Lab Grid with 2 columns and well-proportioned height
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(filteredLabs) { lab ->
                    LabCard(lab = lab, onClick = { onSelectLab(lab.id) })
                }
            }
        }

        if (showSettings) {
            SettingsBottomSheet(
                motionSettings = motionSettings,
                onDismiss = { showSettings = false }
            )
        }
    }
}

@Composable
fun LabCard(
    lab: Lab,
    onClick: () -> Unit
) {
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(185.dp)
            .clickable(onClick = onClick),
        cornerRadius = 22.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    text = lab.title,
                    style = AppTheme.typography.heading,
                    color = AppTheme.colors.onBackground,
                    maxLines = 1
                )
                Text(
                    text = lab.subtitle,
                    style = AppTheme.typography.label,
                    color = AppTheme.colors.onBackground.copy(alpha = 0.7f),
                    maxLines = 3
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    color = AppTheme.colors.primary.copy(alpha = 0.25f),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = lab.category.title,
                        style = AppTheme.typography.label,
                        color = AppTheme.colors.primary,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
                Text(
                    text = "→",
                    style = AppTheme.typography.heading,
                    color = AppTheme.colors.primary
                )
            }
        }
    }
}
