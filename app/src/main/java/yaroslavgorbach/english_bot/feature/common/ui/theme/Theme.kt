package yaroslavgorbach.english_bot.feature.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColorScheme()

private val LightColorPalette = lightColorScheme()

@Composable
fun AppTheme(isDarkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (isDarkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    rememberSystemUiController().setSystemBarsColor(
        color = if (isDarkTheme) DarkColorPalette.background else LightColorPalette.background,
        darkIcons = isDarkTheme.not()
    )

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}