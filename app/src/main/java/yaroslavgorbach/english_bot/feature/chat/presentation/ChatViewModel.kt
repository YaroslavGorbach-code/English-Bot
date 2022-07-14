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

    private val botEngine: BotEngine
        get() = BotEngine(botQuestionsFactory)

    init {
        updateBotName()
        getMessages()
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
                _state.update { state ->
                    state.copy(messages = messages)
                }
            }
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
        }
    }
}