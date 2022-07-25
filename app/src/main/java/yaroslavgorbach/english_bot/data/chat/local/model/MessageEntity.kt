package yaroslavgorbach.english_bot.data.chat.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.domain.chat.model.MessageType

enum class ContentType {
    ONLY_TEXT,
    WITH_MUST_WORDS,
    WITH_ANSWER_VARIANTS
}

@Entity
data class MessageEntity(
    @PrimaryKey
    val id: String,
    val botName: BotName,
    val messageType: MessageType,
    @Embedded
    val content: Content
) {

    data class Content(
        val text: String,
        val answerVariants: List<String>? = null,
        val wordsToUse: List<String>? = null,
        val type: ContentType
    )
}