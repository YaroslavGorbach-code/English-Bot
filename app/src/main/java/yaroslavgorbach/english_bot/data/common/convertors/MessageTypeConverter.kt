package yaroslavgorbach.english_bot.data.common.convertors

import androidx.room.TypeConverter
import yaroslavgorbach.english_bot.data.chat.local.MessageType

object MessageTypeConverter {
    @TypeConverter
    fun toEnum(value: String) = enumValueOf<MessageType>(value)

    @TypeConverter
    fun fromEnum(value: MessageType) = value.name
}