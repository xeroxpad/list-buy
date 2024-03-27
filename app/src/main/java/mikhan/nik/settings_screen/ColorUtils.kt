package mikhan.nik.settings_screen

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import mikhan.nik.ui.theme.LightGreen


object ColorUtils {
    val colorList = listOf(
        "#FFCCC2DC",
        "#F44336",
        "#E91E63",
        "#3F51B5",
        "#2196F3",
        "#03A9F4",
        "#00BCD4",
        "#009688",
        "#4CAF50",
        "#8BC34A",
        "#FFEB3B",
        "#FFC107",
        "#FF9800",
        "#FF5722",
    )

    fun getProgressColor (progress: Float): Color {
        return when (progress) {
            in 0.0..0.339 -> Red
            in 0.34..0.669 -> Yellow
            in 0.67..1.0 -> Green
            else -> Red
        }
    }
}