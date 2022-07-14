package yaroslavgorbach.english_bot.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import yaroslavgorbach.english_bot.core.UiMessage
import yaroslavgorbach.english_bot.core.UiMessageManager

abstract class BaseViewModel<State, Action, Message>(initialState: State) : ViewModel() {

    protected val uiMessageManager: UiMessageManager<Message> = UiMessageManager()

    protected val pendingActions = MutableSharedFlow<Action>()

    protected val _state: MutableStateFlow<State> = MutableStateFlow(initialState)

    val state: StateFlow<State>
        get() = _state

    init {
        viewModelScope.launch {
            pendingActions.collect(::onNewAction)
        }

        viewModelScope.launch {
            uiMessageManager.message.filterNotNull().collect(::onNewUiMessage)
        }
    }

    fun submitAction(action: Action) {
        viewModelScope.launch {
            pendingActions.emit(action)
        }
    }

    fun clearMessage(id: Long) {
        viewModelScope.launch {
            uiMessageManager.clearMessage(id)
        }
    }

    protected abstract fun onNewAction(action: Action)

    abstract fun onNewUiMessage(message: UiMessage<Message>)
}