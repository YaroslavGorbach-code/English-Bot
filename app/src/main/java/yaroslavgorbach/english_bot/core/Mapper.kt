package yaroslavgorbach.english_bot.core

interface Mapper<Input, Output> {
    fun map(input: Input): Output
    fun reverse(input: Output): Input
}