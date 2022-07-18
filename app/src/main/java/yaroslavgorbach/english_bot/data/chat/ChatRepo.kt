package yaroslavgorbach.english_bot.data.chat

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage

interface ChatRepo {
    fun getMessages(botName: BotName): Flow<List<ChatMessage>>
    suspend fun saveMessage(message: ChatMessage)
}