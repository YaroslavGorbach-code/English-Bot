package yaroslavgorbach.english_bot.data.chat.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import yaroslavgorbach.english_bot.data.chat.local.model.Message
import yaroslavgorbach.english_bot.data.chat.local.model.MessageType
import yaroslavgorbach.english_bot.data.common.model.BotName

interface ChatLocalDataSource {
    fun getMessages(name: BotName): Flow<List<Message>>
}

class ChatLocalDataSourceImp : ChatLocalDataSource {
    override fun getMessages(name: BotName): Flow<List<Message>> {
        return flowOf(
            listOf(
                Message(
                    id = 1,
                    order = 0,
                    messageType = MessageType.BOT,
                    content = Message.Content("Test message from bot", emptyList(), null)
                ),
                Message(
                    id = 2,
                    order = 1,
                    messageType = MessageType.ME,
                    content = Message.Content("Test responce from me", emptyList(), null)
                )
            )
        )
    }
}