package yaroslavgorbach.english_bot.feature.bots.presentation

import kotlinx.coroutines.flow.update
import yaroslavgorbach.english_bot.base.BaseViewModel
import yaroslavgorbach.english_bot.core.UiMessage
import yaroslavgorbach.english_bot.feature.bots.model.BotsAction
import yaroslavgorbach.english_bot.feature.bots.model.BotsState
import yaroslavgorbach.english_bot.feature.bots.model.BotsUiMessage

class BotsViewModel : BaseViewModel<BotsState, BotsAction, BotsUiMessage>() {

    override fun onNewUiMessage(message: UiMessage<BotsUiMessage>) {
        _state.update { state ->
            state?.copy(message = message)
        }
    }

}