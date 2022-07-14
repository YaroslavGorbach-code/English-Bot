package yaroslavgorbach.english_bot.domain.chat

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import yaroslavgorbach.english_bot.domain.chat.factory.BotQuestionsFactory
import yaroslavgorbach.english_bot.domain.chat.model.BotQuestion

class BotEngine(botQuestionsFactory: BotQuestionsFactory) {

    private val _question = MutableSharedFlow<BotQuestion>()
    val question: SharedFlow<BotQuestion> = _question

    private val questions: List<BotQuestion> = botQuestionsFactory.create()

    suspend fun acceptAnswer(answer: String, questionId: Int) {
        when (val currentQuestion = questions.find { it.id == questionId }) {
            is BotQuestion.Text -> {
                _question.emit(questions[questions.indexOfFirst { it.id == currentQuestion.nextId }])
            }
            is BotQuestion.TextWithMustWords -> {
                _question.emit(questions[questions.indexOfFirst { it.id == currentQuestion.nextId }])
            }
            is BotQuestion.WithVariants -> {
                val nextId = currentQuestion.variants.first { it.text == answer }.nextQuestionId
                val nextQuestion = questions[questions.indexOfFirst { it.id == nextId }]
                _question.emit(nextQuestion)
            }
            null -> {
                throw Exception("Can not find a question with id $questionId")
            }
        }
    }
}