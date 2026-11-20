package ucb.vargasn.project.core.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

enum class LabCategory(val title: String) {
    BASICS("Fundamentos"),
    MOTION("Transiciones"),
    GESTURES("Gestos"),
    LAYOUT("Diseño & Listas"),
    CANVAS("Canvas & Texto")
}

data class Lab(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val category: LabCategory,
    val accentColorHex: String,
    val content: @Composable (Modifier, Int) -> Unit
)
