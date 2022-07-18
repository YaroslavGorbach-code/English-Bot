package yaroslavgorbach.english_bot.di.chat

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yaroslavgorbach.english_bot.core.Mapper
import yaroslavgorbach.english_bot.data.BotsDatabase
import yaroslavgorbach.english_bot.data.chat.ChatRepo
import yaroslavgorbach.english_bot.data.chat.ChatRepoImp
import yaroslavgorbach.english_bot.data.chat.dao.ChatDao
import yaroslavgorbach.english_bot.data.chat.local.ChatLocalDataSource
import yaroslavgorbach.english_bot.data.chat.local.ChatLocalDataSourceImp
import yaroslavgorbach.english_bot.data.chat.local.model.MessageEntity
import yaroslavgorbach.english_bot.data.chat.mapper.ChatMessageToMessageEntityMapper
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ChatDataModule {

    @Provides
    @Singleton
    fun provideChatDataSource(chatDao: ChatDao): ChatLocalDataSource {
        return ChatLocalDataSourceImp(chatDao)
    }

    @Provides
    fun provideChatMessageToMessageEntityMapper(): Mapper<ChatMessage, MessageEntity> {
        return ChatMessageToMessageEntityMapper()
    }

    @Provides
    @Singleton
    fun provideChatRepo(
        chatLocalDataSource: ChatLocalDataSource,
        mapper: Mapper<ChatMessage, MessageEntity>
    ): ChatRepo {
        return ChatRepoImp(chatLocalDataSource, mapper)
    }

    @Provides
    @Singleton
    fun provideChatDao(database: BotsDatabase): ChatDao {
        return database.chatDao
    }
}