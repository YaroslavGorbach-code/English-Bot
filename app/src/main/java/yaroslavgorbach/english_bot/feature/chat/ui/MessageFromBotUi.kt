package yaroslavgorbach.english_bot.feature.chat.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import yaroslavgorbach.english_bot.R
import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage
import yaroslavgorbach.english_bot.domain.chat.model.MessageType
import yaroslavgorbach.english_bot.feature.common.ui.theme.extraLightGray
import yaroslavgorbach.english_bot.feature.common.ui.theme.messageTextColor
import yaroslavgorbach.english_bot.feature.common.ui.theme.subtitleColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageFromBotUi(
    message: ChatMessage,
    botName: BotName,
    onVariantChosen: (variant: String, id: String) -> Unit
) {
    Column {
        Card(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(end = 50.dp, start = 16.dp),
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
                        Column {
                            MessageText(message = message.text)
                            WordsToUse(words = message.mustWordsString)
                        }
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
                    ChatMessage.Loading -> {}
                }
        }
        BotName(botName, message is ChatMessage.Loading)
    }
}

@Composable
private fun BotName(botName: BotName, isThinking: Boolean) {
    Row {
        Image(
            ImageVector.vectorResource(id = R.drawable.ic_bot),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(start = 16.dp)
        )
        if (isThinking) {
            Thinking(
                modifier = Modifier
                    .align(CenterVertically)
                    .padding(start = 8.dp)
            )
        } else {
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
private fun Thinking(modifier: Modifier = Modifier) {
    val circles = listOf(
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) },
        remember { Animatable(initialValue = 0f) }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(key1 = animatable, block = {
            delay(index * 100L)
            animatable.animateTo(
                1f, animationSpec = infiniteRepeatable(
                    animation = keyframes {
                        durationMillis = 1200
                        0.0f at 0 with LinearOutSlowInEasing
                        1.0f at 300 with LinearOutSlowInEasing
                        0.0f at 600 with LinearOutSlowInEasing
                        0.0f at 1200 with LinearOutSlowInEasing
                    },
                    repeatMode = RepeatMode.Restart
                )
            )
        })
    }

    val circleValues = circles.map { it.value }
    val distance = with(LocalDensity.current) { 20.dp.toPx() }
    val lastIndex = 2

    Row(modifier = modifier.wrapContentWidth()) {
        circleValues.forEachIndexed { index: Int, value: Float ->
            Box(modifier = Modifier
                .size(12.dp)
                .align(CenterVertically)
                .graphicsLayer { translationY = -value * distance }
                .background(
                    color = extraLightGray(),
                    shape = CircleShape
                ))

            if (index != lastIndex) {
                Spacer(modifier = Modifier.width(4.dp))
            }
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
        style = MaterialTheme.typography.bodyMedium,
        text = message,
        modifier = modifier.padding(top = 12.dp, start = 12.dp, end = 12.dp),
        color = messageTextColor(),
        fontSize = 16.sp
    )
}

@Composable
private fun WordsToUse(words: String) {
    Column(modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp)) {
        Text(
            style = MaterialTheme.typography.bodyMedium,
            text = stringResource(id = R.string.use_words_text),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 14.sp
        )
        Text(
            style = MaterialTheme.typography.bodySmall,
            text = words,
            color = subtitleColor(),
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PuzzlePreview() {
    MaterialTheme {
        MessageFromBotUi(
            message = ChatMessage.Text(
                "1",
                BotName.GAME_OF_THRONES,
                "0",
                "test",
                MessageType.BOT
            ),
            botName = BotName.GAME_OF_THRONES,
            onVariantChosen = { string, id -> }
        )
    }
}