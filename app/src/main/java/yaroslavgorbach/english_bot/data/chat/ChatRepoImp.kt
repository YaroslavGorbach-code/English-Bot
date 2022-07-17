package yaroslavgorbach.english_bot.data.chat

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.english_bot.data.chat.local.ChatLocalDataSource
import yaroslavgorbach.english_bot.data.chat.local.model.Message
import yaroslavgorbach.english_bot.data.common.model.BotName

class ChatRepoImp(private val localDataSource: ChatLocalDataSource) : ChatRepo {
    override fun getMessages(botName: BotName): Flow<List<Message>> {
        return localDataSource.getMessages(botName)
    }

    override suspend fun saveMessage(message: Message) {
        localDataSource.saveMessage(message)
    }
}