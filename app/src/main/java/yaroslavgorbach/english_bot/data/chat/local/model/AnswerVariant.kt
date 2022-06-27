package yaroslavgorbach.english_bot.data.chat.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AnswerVariant(
    @PrimaryKey val id: Int,
    val messageId: Int,
    val text: String
)