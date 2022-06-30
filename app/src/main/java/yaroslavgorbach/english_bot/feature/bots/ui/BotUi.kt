package yaroslavgorbach.english_bot.feature.bots.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yaroslavgorbach.english_bot.data.bots.local.model.Bot
import yaroslavgorbach.english_bot.feature.common.ui.theme.Yellow
import yaroslavgorbach.english_bot.feature.common.ui.theme.subtitleColor
import yaroslavgorbach.english_bot.utills.clickableSingle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotUi(bot: Bot, onBot: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(start = 24.dp, end = 24.dp, top = 16.dp)
            .clickableSingle { onBot() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            if (bot.isFinish.not()) {
                Icon(
                    Icons.Default.EmojiEvents,
                    contentDescription = "",
                    tint = Yellow,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(End)
                        .size(20.dp),
                )
            } else {
                CircularProgressIndicator(
                    progress = bot.progress,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(End)
                        .size(20.dp),
                    strokeWidth = 2.dp
                )
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                Image(
                    ImageVector.vectorResource(id = bot.iconRes),
                    contentScale = ContentScale.Fit,
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .align(CenterVertically)
                )
                Column(
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = stringResource(id = bot.name.resId),
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = stringResource(id = bot.descriptionRes),
                        fontSize = 12.sp,
                        color = subtitleColor(),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}