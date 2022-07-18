package yaroslavgorbach.english_bot.feature.chat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import yaroslavgorbach.english_bot.R
import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage
import yaroslavgorbach.english_bot.domain.chat.model.MessageType
import yaroslavgorbach.english_bot.feature.common.ui.theme.messageTextColor
import yaroslavgorbach.english_bot.feature.common.ui.theme.subtitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageFromBotUi(
    message: ChatMessage,
    botName: BotName,
    onVariantChosen: (String, Int) -> Unit
) {
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
            when (message) {
                is ChatMessage.Text -> {
                    MessageText(
                        modifier = Modifier.padding(bottom = 12.dp),
                        message = message.text
                    )
                }
                is ChatMessage.TextWithMustWords -> {

                }
                is ChatMessage.WithVariants -> {
                    Column {
                        MessageText(message = message.text)
                        Variants(
                            modifier = Modifier.align(CenterHorizontally),
                            variants = message.variants,
                            onVariantClick = { text ->
                                onVariantChosen(text, message.id)
                            }
                        )
                    }
                }
            }
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

@Composable
private fun Variants(
    modifier: Modifier,
    variants: List<ChatMessage.WithVariants.Variant>,
    onVariantClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .padding(bottom = 8.dp, top = 4.dp)
    ) {
        variants.forEach { variant ->
            Button(
                onClick = { onVariantClick(variant.text) },
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = variant.text)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
private fun MessageText(modifier: Modifier = Modifier, message: String) {
    Text(
        style = MaterialTheme.typography.displayMedium,
        text = message,
        modifier = modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
        color = messageTextColor(),
        fontSize = 16.sp
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PuzzlePreview() {
    MaterialTheme() {
        MessageFromBotUi(
            message = ChatMessage.Text(
                1,
                BotName.GAME_OF_THRONES,
                0,
                "test",
                MessageType.BOT
            ),
            BotName.GAME_OF_THRONES,
            onVariantChosen = { string, id ->

            }
        )
    }
}