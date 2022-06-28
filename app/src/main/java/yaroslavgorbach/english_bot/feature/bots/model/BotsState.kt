package yaroslavgorbach.english_bot.feature.bots.model

import androidx.datastore.preferences.protobuf.Empty
import yaroslavgorbach.english_bot.core.UiMessage
import yaroslavgorbach.english_bot.data.bots.local.model.Bot

data class BotsState(
    val message: UiMessage<BotsUiMessage>?,
    val bots: List<Bot>
){
    companion object{
        val Empty  = BotsState(message = null, bots = emptyList())
    }
}