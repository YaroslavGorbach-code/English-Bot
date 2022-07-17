package yaroslavgorbach.english_bot.data.chat.local

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.english_bot.data.chat.dao.ChatDao
import yaroslavgorbach.english_bot.data.chat.local.model.Message
import yaroslavgorbach.english_bot.data.common.model.BotName

interface ChatLocalDataSource {
    fun getMessages(name: BotName): Flow<List<Message>>
    suspend fun saveMessage(message: Message)
}

class ChatLocalDataSourceImp(private val chatDao: ChatDao) : ChatLocalDataSource {
    override fun getMessages(name: BotName): Flow<List<Message>> {
        return chatDao.getMessages(name)
    }

    override suspend fun saveMessage(message: Message) {
        return chatDao.insertMessage(message)
    }
}