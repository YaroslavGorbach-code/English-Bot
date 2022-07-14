package yaroslavgorbach.english_bot.domain.chat.model

sealed class BotQuestion(open val id: Int) {
    data class WithVariants(
        override val id: Int,
        val text: String,
        val variants: List<Variant>,
    ) : BotQuestion(id) {
        data class Variant(val text: String, val nextQuestionId: Int)
    }

    data class Text(
        override val id: Int,
        val nextId: Int,
        val text: String,
    ) : BotQuestion(id)

    data class TextWithMustWords(
        override val id: Int,
        val nextId: Int,
        val text: String,
        val mustWords: List<String>,
    ) : BotQuestion(id)
}
