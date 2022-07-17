package yaroslavgorbach.english_bot.data.chat.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.english_bot.data.chat.local.model.Message
import yaroslavgorbach.english_bot.data.common.model.BotName

@Dao
interface ChatDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertMessage(message: Message)

    @Query("SELECT * FROM message WHERE botName = :botName")
    fun getMessages(botName: BotName): Flow<List<Message>>
}