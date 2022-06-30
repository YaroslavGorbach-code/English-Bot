package yaroslavgorbach.english_bot.feature.common.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import yaroslavgorbach.english_bot.feature.common.ui.theme.LightGray
import yaroslavgorbach.english_bot.utills.clickableSingle

@Composable
fun Toolbar(title: String, onBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
    ) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = null,
            tint = LightGray,
            modifier = Modifier
                .clickableSingle { onBack() }
                .align(CenterVertically)
                .padding(8.dp)
                .size(28.dp)
        )
        Title(
            text = title,
            modifier = Modifier
                .align(CenterVertically)
                .weight(1f)
                .padding(end = 36.dp)
        )
    }
}