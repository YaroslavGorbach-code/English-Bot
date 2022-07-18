package yaroslavgorbach.english_bot.domain.chat.factory

import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage

interface BotQuestionsFactory {
    fun create(): List<ChatMessage>
}