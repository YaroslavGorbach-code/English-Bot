package yaroslavgorbach.english_bot.di.chat

import androidx.lifecycle.SavedStateHandle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import yaroslavgorbach.english_bot.BOT_NAME_ARG
import yaroslavgorbach.english_bot.core.Mapper
import yaroslavgorbach.english_bot.data.BotsDatabase
import yaroslavgorbach.english_bot.domain.chat.ChatRepo
import yaroslavgorbach.english_bot.data.chat.ChatRepoImp
import yaroslavgorbach.english_bot.data.chat.dao.ChatDao
import yaroslavgorbach.english_bot.data.chat.local.ChatLocalDataSource
import yaroslavgorbach.english_bot.data.chat.local.model.MessageEntity
import yaroslavgorbach.english_bot.data.chat.mapper.ChatMessageToMessageEntityMapper
import yaroslavgorbach.english_bot.data.chat.ChatInterractorImpl
import yaroslavgorbach.english_bot.domain.chat.ChatInterractor
import yaroslavgorbach.english_bot.domain.chat.factory.BotQuestionsAbstractFactory
import yaroslavgorbach.english_bot.domain.chat.factory.BotQuestionsFactory
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class ChatDomainModule {

    @Provides
    fun provideChatInterractor(factory: BotQuestionsFactory): ChatInterractor {
        return ChatInterractorImpl(factory)
    }

    @Provides
    fun provideBotQuestionsAbstractFactory(): BotQuestionsAbstractFactory {
        return BotQuestionsAbstractFactory()
    }

    @Provides
    fun provideBotQuestionsFactory(savedState: SavedStateHandle, abstractFactory: BotQuestionsAbstractFactory): BotQuestionsFactory {
        return abstractFactory.create(savedState[BOT_NAME_ARG]!!)
    }
}