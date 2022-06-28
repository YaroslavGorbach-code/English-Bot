package yaroslavgorbach.english_bot.feature.common.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import yaroslavgorbach.english_bot.feature.common.ui.theme.subtitleColor

@Composable
fun Title(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = 24.sp,
        style = MaterialTheme.typography.labelLarge,
        textAlign = textAlign
    )
}

@Composable
fun Subtitle(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
) {
    Text(
        modifier = modifier,
        text = text,
        color = subtitleColor(),
        fontSize = 16.sp,
        style = MaterialTheme.typography.titleSmall,
        textAlign = textAlign
    )
}
