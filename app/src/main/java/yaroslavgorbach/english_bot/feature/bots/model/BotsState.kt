package yaroslavgorbach.english_bot.feature.bots.model

import yaroslavgorbach.english_bot.core.UiMessage

data class BotsState(
    val message: UiMessage<BotsUiMessage>?,
    val bots: List<String>
)