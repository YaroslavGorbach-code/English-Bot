package yaroslavgorbach.english_bot.data.bots

import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.english_bot.data.bots.local.model.Bot

interface BotsRepo {
    fun getBots(): Flow<List<Bot>>
}