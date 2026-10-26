package ucb.vargasn.project.components.icons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import ucb.vargasn.project.theme.AppTheme

@Composable
fun UserIcon(
    initials: String = "U",
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(AppTheme.colors.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            color = AppTheme.colors.onPrimary,
            style = AppTheme.typography.label
        )
    }
}
