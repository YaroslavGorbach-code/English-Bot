package yaroslavgorbach.english_bot.data.common.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import yaroslavgorbach.english_bot.data.chat.local.model.MessageType

object MessageTypeConverter {
    @TypeConverter
    fun toEnum(value: String) = enumValueOf<MessageType>(value)

    @TypeConverter
    fun fromEnum(value: MessageType) = value.name

    @TypeConverter
    fun restoreList(listOfString: String): List<String> {
        return Gson().fromJson(listOfString, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<String>): String {
        return Gson().toJson(listOfString)
    }
}