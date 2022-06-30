package yaroslavgorbach.english_bot.feature.chat.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import yaroslavgorbach.english_bot.data.chat.local.model.Message
import yaroslavgorbach.english_bot.data.chat.local.model.MessageType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageFromBotUi(message: Message) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(end = 50.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(topStart = 50f, topEnd = 50f, bottomEnd = 40f, bottomStart = 0f)
    ) {
        Text(text = message.content.text, modifier = Modifier.padding(8.dp))
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
                MessageType.ME,
                Message.Content("test", emptyList(), "")
            )
        )
    }
}