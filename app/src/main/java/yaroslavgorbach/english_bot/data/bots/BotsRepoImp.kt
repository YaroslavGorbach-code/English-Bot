package yaroslavgorbach.english_bot.data.bots

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.english_bot.data.bots.local.BotsLocalDataSource
import yaroslavgorbach.english_bot.data.bots.local.model.Bot

class BotsRepoImp(private val botsLocalDataSource: BotsLocalDataSource) : BotsRepo {
    override fun getBots(): Flow<List<Bot>> {
        return botsLocalDataSource.getBots()
    }
}