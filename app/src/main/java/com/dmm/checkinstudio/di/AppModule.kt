package com.dmm.checkinstudio.di

import android.content.Context
import androidx.room.Room
import com.dmm.checkinstudio.db.CheckInDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesCheckInDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        CheckInDatabase::class.java,
        "checkin_data_database"
    ).build()

    @Provides
    @Singleton
    fun providesCheckInDAO(db:CheckInDatabase) = db.getCheckInDAO()

    @Provides
    @Singleton
    fun providesUsuarioDAO(db:CheckInDatabase) = db.getUsuarioDAO()

}