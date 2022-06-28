package yaroslavgorbach.english_bot.di.bots

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yaroslavgorbach.english_bot.data.bots.BotsRepo
import yaroslavgorbach.english_bot.data.bots.BotsRepoImp
import yaroslavgorbach.english_bot.data.bots.local.BotsLocalDataSource
import yaroslavgorbach.english_bot.data.bots.local.BotsLocalDataSourceImp

@Module
@InstallIn(SingletonComponent::class)
class BotsDataModule {

    @Provides
    fun provideBotsDataSource(): BotsLocalDataSource {
        return BotsLocalDataSourceImp()
    }

    @Provides
    fun provideBotsRepo(botsLocalDataSource: BotsLocalDataSource): BotsRepo {
        return BotsRepoImp(botsLocalDataSource)
    }
}