package yaroslavgorbach.english_bot.feature.chat.model

import yaroslavgorbach.english_bot.core.UiMessage
import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage

data class ChatState(
    val botName: BotName,
    val message: UiMessage<ChatUiMessage>?,
    val messages: List<ChatMessage>,
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