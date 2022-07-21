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
            ChatMessage.Text(
                id = 3,
                botName = BotName.GAME_OF_THRONES,
                nextId = 4, "Why explain in details",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 4,
                botName = BotName.GAME_OF_THRONES,
                nextId = 5, "Do you like it?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 5,
                botName = BotName.GAME_OF_THRONES,
                nextId = 6, "Do you like it?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 6,
                botName = BotName.GAME_OF_THRONES,
                nextId = 7, "Do you like it?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 7,
                botName = BotName.GAME_OF_THRONES,
                nextId = 8, "Do you like it?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 8,
                botName = BotName.GAME_OF_THRONES,
                nextId = 9, "Do you like it?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 9,
                botName = BotName.GAME_OF_THRONES,
                nextId = 10, "Do you like it?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 10,
                botName = BotName.GAME_OF_THRONES,
                nextId = 11, "Do you like it?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 11,
                botName = BotName.GAME_OF_THRONES,
                nextId = 12, "Do you like it?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 12,
                botName = BotName.GAME_OF_THRONES,
                nextId = 13, "Do you like it?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 13,
                botName = BotName.GAME_OF_THRONES,
                nextId = 14, "Do you like it?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = 14,
                botName = BotName.GAME_OF_THRONES,
                nextId = 15, "Do you like it?",
                type = MessageType.BOT
            ),
        )
    }
}