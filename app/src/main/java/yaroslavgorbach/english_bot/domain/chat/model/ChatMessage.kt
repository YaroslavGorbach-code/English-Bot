package yaroslavgorbach.english_bot.domain.chat.model

import yaroslavgorbach.english_bot.data.common.model.BotName

enum class MessageType {
    BOT,
    ME
}

sealed class ChatMessage(
    open val id: Int,
    open val botName: BotName,
    open val type: MessageType
) {
    data class WithVariants(
        override val id: Int,
        override val botName: BotName,
        val text: String,
        val variants: List<Variant>,
    ) : ChatMessage(id, botName, MessageType.BOT) {
        data class Variant(val text: String, val nextQuestionId: Int = 0)
    }

    data class Text(
        override val id: Int = 0,
        override val botName: BotName,
        val nextId: Int = 0,
        val text: String,
        override val type: MessageType
    ) : ChatMessage(id, botName, type)

    data class TextWithMustWords(
        override val id: Int,
        override val botName: BotName,
        val nextId: Int = 0,
        val text: String,
        val mustWords: List<String>,
    ) : ChatMessage(id, botName, MessageType.BOT)

    object Loading : ChatMessage(-1, BotName.GAME_OF_THRONES, MessageType.BOT)
}
