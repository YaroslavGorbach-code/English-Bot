package yaroslavgorbach.english_bot.feature.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightBlue = Color(0xFF4197E3)
val LightGray = Color(0xFF9F9F9F)
val DarkGray = Color(0xFF505050)
val Yellow = Color(0xFFFFF200)

@Composable
fun subtitleColor(isDarkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (isDarkTheme) LightGray else LightGray
}

@Composable
fun messageTextColor(isDarkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (isDarkTheme) DarkGray else DarkGray
}
