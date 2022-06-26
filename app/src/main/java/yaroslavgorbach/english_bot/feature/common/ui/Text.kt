package yaroslavgorbach.english_bot.feature.common.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Title(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.displayLarge,
        textAlign = textAlign
    )
}
