package yaroslavgorbach.english_bot.feature.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Blue700 = Color(0xFF6E58F3)
val Blue400 = Color(0xFF9181F2)
val Blue200 = Color(0xFFA396F1)
val Pink200 = Color(0xFFE040FB)
val LightBlue = Color(0xFF304FFE)
val LightGray = Color(0xFF9F9F9F)

@Composable
fun subtitleColor(isDarkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (isDarkTheme) LightGray else LightGray
}
