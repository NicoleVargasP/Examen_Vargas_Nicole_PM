package ucb.vargasn.project.core.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class MotionSettingsState {
    var speedMultiplier by mutableStateOf(1f) // 0.25f to 2f
    var reduceMotion by mutableStateOf(false)

    fun scaleDuration(durationMillis: Int): Int {
        if (reduceMotion) return 0
        return (durationMillis / speedMultiplier).toInt().coerceAtLeast(1)
    }

    fun scaleMillis(millis: Long): Long {
        if (reduceMotion) return 0L
        return (millis / speedMultiplier).toLong().coerceAtLeast(1L)
    }
}

val LocalMotionSettings = compositionLocalOf { MotionSettingsState() }
