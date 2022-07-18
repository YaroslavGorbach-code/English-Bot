package yaroslavgorbach.english_bot.data.chat.local

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.english_bot.data.chat.dao.ChatDao
import yaroslavgorbach.english_bot.data.chat.local.model.MessageEntity
import yaroslavgorbach.english_bot.data.common.model.BotName

interface ChatLocalDataSource {
    fun getMessages(name: BotName): Flow<List<MessageEntity>>
    suspend fun saveMessage(messageEntity: MessageEntity)
}

class ChatLocalDataSourceImp(private val chatDao: ChatDao) : ChatLocalDataSource {
    override fun getMessages(name: BotName): Flow<List<MessageEntity>> {
        return chatDao.getMessages(name)
    }

    override suspend fun saveMessage(messageEntity: MessageEntity) {
        return chatDao.insertMessage(messageEntity)
    }
}