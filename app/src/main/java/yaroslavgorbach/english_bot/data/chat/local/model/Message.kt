package yaroslavgorbach.english_bot.data.chat.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    @Embedded
    val content: Content
) {

    data class Content(
        val text: String,
        val answerVariants: List<String>,
        val wordsToUse: String?
    )
}