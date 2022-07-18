package yaroslavgorbach.english_bot.domain.chat.factory

import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage
import yaroslavgorbach.english_bot.domain.chat.model.MessageType

class GameOfThronesFactory : BotQuestionsFactory {
    override fun create(): List<ChatMessage> {
        return listOf(
            ChatMessage.WithVariants(
                id = 0,
                botName = BotName.GAME_OF_THRONES,
                text = "Have you watched game of thrones?",
                variants = listOf(
                    ChatMessage.WithVariants.Variant("Yes", 1),
                    ChatMessage.WithVariants.Variant("No", 4),
                )
            ),
            ChatMessage.Text(
                id = 1,
                botName = BotName.GAME_OF_THRONES,
                nextId = 2, "Tell me what this series is about?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 2,
                botName = BotName.GAME_OF_THRONES,
                nextId = 3, "Would you like to live in that world",
                type = MessageType.BOT
            ),
        )
    }
}