package yaroslavgorbach.english_bot.domain.chat

import kotlinx.coroutines.flow.SharedFlow
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage

interface ChatInterractor {
    val question: SharedFlow<ChatMessage>
    val answer: SharedFlow<String>
    val isThinking: SharedFlow<Boolean>
    suspend fun answer(answer: String, questionId: String)
    suspend fun startConversation()
}