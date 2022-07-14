package yaroslavgorbach.english_bot.feature.chat.model

import yaroslavgorbach.english_bot.core.UiMessage
import yaroslavgorbach.english_bot.data.chat.local.model.Message
import yaroslavgorbach.english_bot.data.common.model.BotName

data class ChatState(
    val botName: BotName,
    val message: UiMessage<ChatUiMessage>?,
    val messages: List<Message>,
    val typedValue: String = ""
) {
    companion object {
        val Empty = ChatState(
            botName = BotName.GAME_OF_THRONES,
            message = null,
            messages = emptyList()
        )
    }
}