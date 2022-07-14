package yaroslavgorbach.english_bot.feature.bots.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import yaroslavgorbach.english_bot.base.BaseViewModel
import yaroslavgorbach.english_bot.core.UiMessage
import yaroslavgorbach.english_bot.data.bots.BotsRepo
import yaroslavgorbach.english_bot.feature.bots.model.BotsAction
import yaroslavgorbach.english_bot.feature.bots.model.BotsState
import yaroslavgorbach.english_bot.feature.bots.model.BotsUiMessage
import javax.inject.Inject

@HiltViewModel
class BotsViewModel @Inject constructor(
    private val botsRepo: BotsRepo
) : BaseViewModel<BotsState, BotsAction, BotsUiMessage>(initialState = BotsState.Empty) {

    override fun onNewUiMessage(message: UiMessage<BotsUiMessage>) {
        _state.update { state -> state.copy(message = message) }
    }

    init {
        getBots()
    }

    private fun getBots() {
        viewModelScope.launch {
            botsRepo.getBots().collect { bots ->
                _state.update { state ->
                    state.copy(bots = bots)
                }
            }
        }
    }

    override fun onNewAction(action: BotsAction) {
        when(action){

        }
    }
}