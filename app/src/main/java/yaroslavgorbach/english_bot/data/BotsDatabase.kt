package yaroslavgorbach.english_bot.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import yaroslavgorbach.english_bot.data.chat.local.Message
import yaroslavgorbach.english_bot.data.common.convertors.MessageTypeConverter

@TypeConverters(MessageTypeConverter::class)
@Database(
    exportSchema = true,
    entities = [
        Message::class,
    ],
    version = 1
)
abstract class BotsDatabase : RoomDatabase() {

}
