package yaroslavgorbach.english_bot.core

interface Factory<T> {
    fun create(): T
}