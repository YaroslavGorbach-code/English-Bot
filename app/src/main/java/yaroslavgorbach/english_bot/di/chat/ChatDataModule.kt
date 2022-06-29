package yaroslavgorbach.english_bot.di.chat

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yaroslavgorbach.english_bot.data.bots.BotsRepo
import yaroslavgorbach.english_bot.data.bots.BotsRepoImp
import yaroslavgorbach.english_bot.data.bots.local.BotsLocalDataSource
import yaroslavgorbach.english_bot.data.bots.local.BotsLocalDataSourceImp
import yaroslavgorbach.english_bot.data.chat.ChatRepo
import yaroslavgorbach.english_bot.data.chat.ChatRepoImp
import yaroslavgorbach.english_bot.data.chat.local.ChatLocalDataSource
import yaroslavgorbach.english_bot.data.chat.local.ChatLocalDataSourceImp

@Module
@InstallIn(SingletonComponent::class)
class ChatDataModule {

    @Provides
    fun provideChatDataSource(): ChatLocalDataSource {
        return ChatLocalDataSourceImp()
    }

    @Provides
    fun provideChatRepo(chatLocalDataSource: ChatLocalDataSource): ChatRepo {
        return ChatRepoImp(chatLocalDataSource)
    }
}