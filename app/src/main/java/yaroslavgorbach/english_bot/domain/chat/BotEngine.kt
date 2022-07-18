package yaroslavgorbach.english_bot.domain.chat

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import yaroslavgorbach.english_bot.domain.chat.factory.BotQuestionsFactory
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage

class BotEngine(botQuestionsFactory: BotQuestionsFactory) {

    private val _question = MutableSharedFlow<ChatMessage>(replay = 0)
    val question: SharedFlow<ChatMessage> = _question

    private val questions: List<ChatMessage> = botQuestionsFactory.create()

    suspend fun acceptAnswer(answer: String, questionId: Int) {
        when (val currentQuestion = questions.find { it.id == questionId }) {
            is ChatMessage.Text -> {
                _question.emit(questions[questions.indexOfFirst { it.id == currentQuestion.nextId }])
            }
            is ChatMessage.TextWithMustWords -> {
                _question.emit(questions[questions.indexOfFirst { it.id == currentQuestion.nextId }])
            }
            is ChatMessage.WithVariants -> {
                val nextId = currentQuestion.variants.first { it.text == answer }.nextQuestionId
                val nextQuestion = questions[questions.indexOfFirst { it.id == nextId }]
                _question.emit(nextQuestion)
            }
            null -> {
                throw Exception("Can not find a question with id $questionId")
            }
        }
    }

    suspend fun startConversation() {
        _question.emit(questions[0])
    }
}