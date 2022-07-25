package yaroslavgorbach.english_bot.data.chat

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import yaroslavgorbach.english_bot.core.Mapper
import yaroslavgorbach.english_bot.data.chat.local.ChatLocalDataSource
import yaroslavgorbach.english_bot.data.chat.local.model.MessageEntity
import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage

class ChatRepoImp(
    private val localDataSource: ChatLocalDataSource,
    private val chatMessageToMessageEntityMapper: Mapper<ChatMessage, MessageEntity>
) : ChatRepo {

    override fun getMessages(botName: BotName): Flow<List<ChatMessage>> {
        return localDataSource.getMessages(botName).map { messages ->
            messages.map { messageEntity: MessageEntity ->
                chatMessageToMessageEntityMapper.reverse(messageEntity)
            }
        }
    }

    override suspend fun saveMessage(message: ChatMessage) {
        withContext(Dispatchers.IO) {
            localDataSource.saveMessage(chatMessageToMessageEntityMapper.map(message))
        }
    }

    override suspend fun clearVariants(messageId: String) {
        withContext(Dispatchers.IO) {
            localDataSource.clearVariants(messageId)
        }
    }
}