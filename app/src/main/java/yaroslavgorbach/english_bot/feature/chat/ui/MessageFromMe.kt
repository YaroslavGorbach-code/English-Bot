package yaroslavgorbach.english_bot.feature.chat.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yaroslavgorbach.english_bot.data.chat.local.model.Message
import yaroslavgorbach.english_bot.data.chat.local.model.MessageType
import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.feature.common.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageFromMeUi(message: Message) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(start = 50.dp, end = 16.dp, top = 12.dp)
                .align(CenterEnd),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(
                topStart = 50f,
                topEnd = 0f,
                bottomEnd = 40f,
                bottomStart = 50f
            ),
            colors = CardDefaults.cardColors(containerColor = LightBlue)
        ) {
            Text(
                style = MaterialTheme.typography.displayMedium,
                text = message.content.text,
                modifier = Modifier.padding(12.dp),
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MessageFromMePreview() {
    MaterialTheme {
        MessageFromMeUi(
            message = Message(
                0,
                2,
                BotName.GAME_OF_THRONES,
                MessageType.ME,
                Message.Content("test", emptyList(), "")
            ),
        )
    }
}