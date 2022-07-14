package yaroslavgorbach.english_bot.domain.chat.factory

import yaroslavgorbach.english_bot.data.common.model.BotName

class BotQuestionsAbstractFactory {
    fun create(botName: BotName): BotQuestionsFactory {
        return when (botName) {
            BotName.GAME_OF_THRONES -> GameOfThronesFactory()
        }
    }
}