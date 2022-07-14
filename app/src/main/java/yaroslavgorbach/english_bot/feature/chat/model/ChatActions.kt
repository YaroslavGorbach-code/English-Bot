package yaroslavgorbach.english_bot.feature.chat.model

sealed class ChatActions {
    class TypeText(val text: String) : ChatActions()
    object SentMessage : ChatActions()
}
