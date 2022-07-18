package yaroslavgorbach.english_bot.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import yaroslavgorbach.english_bot.data.chat.dao.ChatDao
import yaroslavgorbach.english_bot.data.chat.local.model.MessageEntity
import yaroslavgorbach.english_bot.data.common.convertors.Convertors

@TypeConverters(Convertors::class)
@Database(
    exportSchema = true,
    entities = [MessageEntity::class],
    version = 1
)
abstract class BotsDatabase : RoomDatabase() {
    abstract val chatDao: ChatDao
}
