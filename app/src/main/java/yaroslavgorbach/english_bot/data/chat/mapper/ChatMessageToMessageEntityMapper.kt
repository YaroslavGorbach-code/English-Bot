package yaroslavgorbach.english_bot.data.chat.mapper

import yaroslavgorbach.english_bot.core.Mapper
import yaroslavgorbach.english_bot.data.chat.local.model.ContentType
import yaroslavgorbach.english_bot.data.chat.local.model.MessageEntity
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage
import yaroslavgorbach.english_bot.domain.chat.model.MessageType

class ChatMessageToMessageEntityMapper : Mapper<ChatMessage, MessageEntity> {

    override fun map(input: ChatMessage): MessageEntity {
        return when (input) {
            is ChatMessage.Text -> {
                MessageEntity(
                    botName = input.botName,
                    messageType = input.type,
                    content = MessageEntity.Content(text = input.text, type = ContentType.ONLY_TEXT)
                )
            }
            is ChatMessage.TextWithMustWords -> {
                MessageEntity(
                    botName = input.botName,
                    messageType = MessageType.BOT,
                    content = MessageEntity.Content(
                        text = input.text,
                        wordsToUse = input.mustWords,
                        type = ContentType.WITH_MUST_WORDS
                    )
                )
            }
            is ChatMessage.WithVariants -> {
                MessageEntity(
                    botName = input.botName,
                    messageType = MessageType.BOT,
                    content = MessageEntity.Content(
                        text = input.text,
                        answerVariants = input.variants.map { it.text },
                        type = ContentType.WITH_ANSWER_VARIANTS
                    )
                )
            }
        }
    }

    override fun reverse(input: MessageEntity): ChatMessage {
        return when (input.content.type) {
            ContentType.ONLY_TEXT -> {
                ChatMessage.Text(
                    id = input.id,
                    botName = input.botName,
                    text = input.content.text,
                    type = input.messageType
                )
            }
            ContentType.WITH_MUST_WORDS -> {
                ChatMessage.TextWithMustWords(
                    id = input.id,
                    botName = input.botName,
                    text = input.content.text,
                    mustWords = input.content.wordsToUse ?: emptyList()
                )
            }
            ContentType.WITH_ANSWER_VARIANTS -> {
                ChatMessage.WithVariants(
                    id = input.id,
                    botName = input.botName,
                    text = input.content.text,
                    variants = input.content.answerVariants?.map {
                        ChatMessage.WithVariants.Variant(it)
                    } ?: emptyList()
                )
            }
        }
    }
}