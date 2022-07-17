package yaroslavgorbach.english_bot.feature.chat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yaroslavgorbach.english_bot.R
import yaroslavgorbach.english_bot.data.chat.local.model.Message
import yaroslavgorbach.english_bot.data.chat.local.model.MessageType
import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.feature.common.ui.theme.messageTextColor
import yaroslavgorbach.english_bot.feature.common.ui.theme.subtitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageFromBotUi(message: Message, botName: BotName) {
    Column {
        Card(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(end = 50.dp, start = 16.dp, top = 12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(
                topStart = 50f,
                topEnd = 50f,
                bottomEnd = 40f,
                bottomStart = 0f
            )
        ) {
            Text(
                style = MaterialTheme.typography.displayMedium,
                text = message.content.text,
                modifier = Modifier.padding(12.dp),
                color = messageTextColor(),
                fontSize = 16.sp
            )
        }
        Row {
            Image(
                ImageVector.vectorResource(id = R.drawable.ic_bot),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 16.dp)
            )
            Text(
                color = subtitleColor(),
                text = "Bot " + stringResource(id = botName.resId),
                fontSize = 14.sp,
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(start = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PuzzlePreview() {
    MaterialTheme() {
        MessageFromBotUi(
            message = Message(
                0,
                2,
                BotName.GAME_OF_THRONES,
                MessageType.ME,
                Message.Content("test", emptyList(), "")
            ),
            BotName.GAME_OF_THRONES
        )
    }
}