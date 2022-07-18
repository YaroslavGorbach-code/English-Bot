package yaroslavgorbach.english_bot.feature.chat.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yaroslavgorbach.english_bot.BOT_NAME_ARG
import yaroslavgorbach.english_bot.base.BaseViewModel
import yaroslavgorbach.english_bot.core.UiMessage
import yaroslavgorbach.english_bot.data.chat.ChatRepo
import yaroslavgorbach.english_bot.data.common.model.BotName
import yaroslavgorbach.english_bot.domain.chat.BotEngine
import yaroslavgorbach.english_bot.domain.chat.factory.BotQuestionsAbstractFactory
import yaroslavgorbach.english_bot.domain.chat.factory.BotQuestionsFactory
import yaroslavgorbach.english_bot.domain.chat.model.ChatMessage
import yaroslavgorbach.english_bot.domain.chat.model.MessageType
import yaroslavgorbach.english_bot.feature.chat.model.ChatActions
import yaroslavgorbach.english_bot.feature.chat.model.ChatState
import yaroslavgorbach.english_bot.feature.chat.model.ChatUiMessage
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepo: ChatRepo,
    savedState: SavedStateHandle
) : BaseViewModel<ChatState, ChatActions, ChatUiMessage>(initialState = ChatState.Empty) {

    private val botName: BotName = savedState[BOT_NAME_ARG]!!

    private val botQuestionsAbstractFactory: BotQuestionsAbstractFactory
        get() = BotQuestionsAbstractFactory()

    private val botQuestionsFactory: BotQuestionsFactory
        get() = botQuestionsAbstractFactory.create(botName)

    private val botEngine: BotEngine = BotEngine(botQuestionsFactory)

    init {
        observeNewBotQuestion()
        observeMyAnswers()
        updateBotName()
        getMessages()
    }

    private fun observeNewBotQuestion() {
        viewModelScope.launch {
            botEngine.question.collect { newQuestion ->
                chatRepo.saveMessage(newQuestion)
            }
        }
    }

    private fun observeMyAnswers() {
        viewModelScope.launch {
            botEngine.answer.collect { answer ->
                saveMyAnswer(answer)
            }
        }
    }

    private suspend fun saveMyAnswer(text: String) {
        chatRepo.saveMessage(
            ChatMessage.Text(
                botName = botName,
                text = text,
                type = MessageType.ME
            )
        )
    }

    override fun onNewUiMessage(message: UiMessage<ChatUiMessage>) {
        _state.update { state -> state.copy(message = message) }
    }

    private fun updateBotName() {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(botName = botName)
            }
        }
    }

    private fun getMessages() {
        viewModelScope.launch {
            chatRepo.getMessages(botName).collect { messages ->
                startConversationIfNeeded(messages.isEmpty())
                _state.update { state -> state.copy(messages = messages) }
            }
        }
    }

    private suspend fun startConversationIfNeeded(hasHeed: Boolean) {
        if (hasHeed) {
            botEngine.startConversation()
        }
    }

    override fun onNewAction(action: ChatActions) {
        when (action) {
            is ChatActions.TypeText -> {
                _state.update { state ->
                    state.copy(typedValue = action.text)
                }
            }
            ChatActions.SentMessage -> {

            }
            is ChatActions.ChooseAnswerVariant -> {
                viewModelScope.launch {
                    botEngine.answer(action.text, action.questionId)
                    chatRepo.clearVariants(action.questionId)
                }
            }
        }
    }
}