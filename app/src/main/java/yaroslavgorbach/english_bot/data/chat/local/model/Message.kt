package yaroslavgorbach.english_bot.data.chat.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import yaroslavgorbach.english_bot.data.chat.local.model.AnswerVariant

enum class MessageType {
    BOT,
    ME
}

@Entity
data class Message(
    @PrimaryKey
    val id: Int,
    val order: Int,
    val messageType: MessageType,
    val content: Content
) {

    data class Content(
        val text: String,
        val answerVariants: List<AnswerVariant>?,
        val wordsToUse: String?
    )
}