package yaroslavgorbach.english_bot.data.common.convertors

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import yaroslavgorbach.english_bot.data.chat.local.model.ContentType
import yaroslavgorbach.english_bot.domain.chat.model.MessageType

object Convertors {
    @TypeConverter
    fun messageTypeToEnum(value: String) = enumValueOf<MessageType>(value)

    @TypeConverter
    fun fromEnumToMessageType(value: MessageType) = value.name

    @TypeConverter
    fun contentTypeToEnum(value: String) = enumValueOf<ContentType>(value)

    @TypeConverter
    fun fromEnumToContentType(value: ContentType) = value.name

    @TypeConverter
    fun restoreList(listOfString: String?): List<String>? {
        return Gson().fromJson(listOfString, object : TypeToken<List<String>?>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<String>?): String? {
        return Gson().toJson(listOfString)
    }
}