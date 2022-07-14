package yaroslavgorbach.english_bot.domain.chat.factory

import yaroslavgorbach.english_bot.domain.chat.model.BotQuestion

interface BotQuestionsFactory {
    fun create(): List<BotQuestion>
}