package yaroslavgorbach.english_bot.domain.chat.model

import yaroslavgorbach.english_bot.data.common.model.BotName

enum class MessageType {
    BOT,
    ME
}

sealed class ChatMessage(
    open val id: String,
    open val botName: BotName,
    open val type: MessageType
) {
    data class WithVariants(
        override val id: String,
        override val botName: BotName,
        val text: String,
        val variants: List<Variant>,
    ) : ChatMessage(id, botName, MessageType.BOT) {
        data class Variant(val text: String, val nextQuestionId: String = "0")
    }

    data class Text(
        override val id: String,
        override val botName: BotName,
        val nextId: String,
        val text: String,
        override val type: MessageType
    ) : ChatMessage(id, botName, type)

    data class TextWithMustWords(
        override val id: String,
        override val botName: BotName,
        val nextId: String,
        val text: String,
        val mustWords: List<String>,
    ) : ChatMessage(id, botName, MessageType.BOT) {
        val mustWordsString: String
            get() = mustWords.joinToString()
    }

    object Loading : ChatMessage("-1", BotName.GAME_OF_THRONES, MessageType.BOT)
}
