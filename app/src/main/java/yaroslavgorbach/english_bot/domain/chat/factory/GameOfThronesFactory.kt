package yaroslavgorbach.english_bot.domain.chat.factory

import yaroslavgorbach.english_bot.domain.chat.model.BotQuestion

class GameOfThronesFactory : BotQuestionsFactory {
    override fun create(): List<BotQuestion> {
        return listOf(
            BotQuestion.WithVariants(
                id = 0,
                text = "Have you watched game of thrones?",
                variants = listOf(
                    BotQuestion.WithVariants.Variant("Yes", 1),
                    BotQuestion.WithVariants.Variant("No", 2),
                )
            ),
            BotQuestion.Text(id = 1, nextId = 2, "Tell me what this series is about?"),
        )
    }
}