package yaroslavgorbach.english_bot.feature.chat.model

sealed class ChatUiMessage {
    data class ScrollToPosition(val position: Int) : ChatUiMessage()
}
