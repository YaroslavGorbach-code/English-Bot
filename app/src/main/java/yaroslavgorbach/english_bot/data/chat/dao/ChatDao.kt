package yaroslavgorbach.english_bot.data.chat.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import yaroslavgorbach.english_bot.data.chat.local.model.MessageEntity
import yaroslavgorbach.english_bot.data.common.model.BotName

@Dao
interface ChatDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertMessage(messageEntity: MessageEntity)

    @Query("SELECT * FROM messageentity WHERE botName = :botName")
    fun getMessages(botName: BotName): Flow<List<MessageEntity>>

    @Query("UPDATE messageentity SET answerVariants = null WHERE id = :id")
    fun clearVariants(id: Int)
}