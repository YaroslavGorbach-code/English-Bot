package yaroslavgorbach.english_bot.di.common

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yaroslavgorbach.english_bot.data.BotsDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreDataModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): BotsDatabase {
        return Room.databaseBuilder(application, BotsDatabase::class.java, "database").build()
    }
}