package yaroslavgorbach.english_bot.domain.chat

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import yaroslavgorbach.english_bot.domain.chat.factory.BotQuestionsFactory
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage

class BotEngine(botQuestionsFactory: BotQuestionsFactory) {

    companion object{
        private const val TIME_TO_THINK = 2000L
    }

    private val _question = MutableSharedFlow<ChatMessage>(replay = 0)
    val question: SharedFlow<ChatMessage> = _question

    private val _answer = MutableSharedFlow<String>(replay = 0)
    val answer: SharedFlow<String> = _answer

    private val _isThinking = MutableSharedFlow<Boolean>(replay = 0)
    val isThinking: SharedFlow<Boolean> = _isThinking

    private val questions: List<ChatMessage> = botQuestionsFactory.create()

    suspend fun answer(answer: String, questionId: String) {
        _answer.emit(answer)
        think(TIME_TO_THINK)
        when (val currentQuestion = questions.find { it.id == questionId }) {
            is ChatMessage.Text -> {
                Log.i("dsdsds", "text ${questionId}")
                _question.emit(questions[questions.indexOfFirst { it.id == currentQuestion.nextId }])
            }
            is ChatMessage.TextWithMustWords -> {
                Log.i("dsdsds", "text with must ${questionId}")
                Log.i("dsdsds", "text with must next id ${currentQuestion.nextId}")
                Log.i("dsdsds", questions.toString())
                _question.emit(questions[questions.indexOfFirst { it.id == currentQuestion.nextId }])
            }
            is ChatMessage.WithVariants -> {
                Log.i("dsdsds", "text with wariants ${questionId}")

                val nextId = currentQuestion.variants.first { it.text == answer }.nextQuestionId
                val nextQuestion = questions[questions.indexOfFirst { it.id == nextId }]
                _question.emit(nextQuestion)
            }
            else -> {
                throw Exception("Can not find a question with id $questionId")
            }
        }
    }

    suspend fun startConversation() {
        think(TIME_TO_THINK)
        _question.emit(questions[0])
    }

    private suspend fun think(time: Long){
        delay(300)
        _isThinking.emit(true)
        delay(time)
        _isThinking.emit(false)
    }
}