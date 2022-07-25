package yaroslavgorbach.english_bot.domain.chat.factory

import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage
import yaroslavgorbach.english_bot.domain.chat.model.MessageType

class GameOfThronesFactory : BotQuestionsFactory {
    override fun create(): List<ChatMessage> {
        return listOf(
            ChatMessage.WithVariants(
                id = "1",
                botName = BotName.GAME_OF_THRONES,
                text = "Did you watch Game of Thrones?",
                variants = listOf(
                    ChatMessage.WithVariants.Variant("Yes", "2"),
                    ChatMessage.WithVariants.Variant("No", "-1000"),
                )
            ),
            ChatMessage.TextWithMustWords(
                id = "2",
                botName = BotName.GAME_OF_THRONES,
                nextId = "3",
                text = "What is this TV series about?",
                mustWords = listOf(
                    "dragons",
                    "winter",
                    "White Walker",
                    "throne",
                    "Westeros",
                    "seven kingdoms"
                )
            ),
            ChatMessage.Text(
                id = "3",
                botName = BotName.GAME_OF_THRONES,
                nextId = "4",
                text = "What do you think is special about Game of Thrones?",
                type = MessageType.BOT
            ),
            ChatMessage.TextWithMustWords(
                id = "4",
                botName = BotName.GAME_OF_THRONES,
                nextId = "5",
                text = "Tell me about your favorite house (family) from the series",
                mustWords = listOf(
                    "Targaryen",
                    "Lannisters",
                    "Starks",
                    "Tyrell",
                    "Martell",
                    "Greyjoys"
                )
            ),
            ChatMessage.Text(
                id = "5",
                botName = BotName.GAME_OF_THRONES,
                nextId = "6",
                text = "Which character from the show is your favorite and why?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = "6",
                botName = BotName.GAME_OF_THRONES,
                nextId = "7",
                text = "What do you think about the phrase \"When you play " +
                        "the game of thrones you win or you die. There is no middle ground.\"?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = "7",
                botName = BotName.GAME_OF_THRONES,
                nextId = "8",
                text = "What do you think about the phrase " +
                        "\"Money buys a man's silence for a time. A bolt in the heart buys it forever.\" ?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = "8",
                botName = BotName.GAME_OF_THRONES,
                nextId = "9",
                text = "What do you think about the phrase" +
                        " \"There's a beast in every man, and it stirs when you put a sword in his hand.\" ?",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = "9",
                botName = BotName.GAME_OF_THRONES,
                nextId = "10",
                text = "who was called Dwarf in the series? Tell me more about that character",
                type = MessageType.BOT
            ),
            ChatMessage.Text(
                id = "10",
                botName = BotName.GAME_OF_THRONES,
                nextId = "11",
                text = "who was called Bastard in the series? Tell me more about that character",
                type = MessageType.BOT
            ),
        )
    }
}