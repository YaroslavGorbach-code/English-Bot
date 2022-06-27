package yaroslavgorbach.english_bot.data.bots.local.model

import yaroslavgorbach.english_bot.data.common.model.BotName

data class Bot(val name: BotName, val description: String, val progress: Int) {
    val isFinish: Boolean
        get() = progress == 100
}