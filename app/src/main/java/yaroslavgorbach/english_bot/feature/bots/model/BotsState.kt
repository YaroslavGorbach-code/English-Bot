package yaroslavgorbach.english_bot.feature.bots.model

import yaroslavgorbach.english_bot.core.UiMessage

data class BotsState(
    override val message: UiMessage<BotsUiMessage>?,
) : BaseState<BotsUiMessage>(message) {

}