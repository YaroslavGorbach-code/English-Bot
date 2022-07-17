package yaroslavgorbach.english_bot.data.chat

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.english_bot.data.chat.local.model.Message
import yaroslavgorbach.english_bot.data.common.model.BotName

interface ChatRepo {
    fun getMessages(botName: BotName): Flow<List<Message>>
    suspend fun saveMessage(message: Message)
}