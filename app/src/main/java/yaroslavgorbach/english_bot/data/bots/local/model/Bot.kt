package yaroslavgorbach.english_bot.data.bots.local.model

import yaroslavgorbach.english_bot.data.common.model.BotName

data class Bot(
    val name: BotName,
    val descriptionRes: Int,
    val iconRes: Int,
    val progress: Float
) {
    val isFinish: Boolean
        get() = progress == 1.0f
}