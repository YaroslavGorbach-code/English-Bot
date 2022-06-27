package yaroslavgorbach.english_bot.data.bots.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import yaroslavgorbach.english_bot.data.bots.local.model.Bot
import yaroslavgorbach.english_bot.data.common.model.BotName

interface BotsLocalDataSource {
    fun getBots(): Flow<List<Bot>>
}

class BotsLocalDataSourceImp() : BotsLocalDataSource {
    override fun getBots(): Flow<List<Bot>> {
        return flowOf(
            listOf(
                Bot(BotName.GAME_OF_THRONES, "test", 0)
            )
        )
    }
}