package com.example.calculator.quotesapp.di

import android.content.Context
import androidx.room.Room
import com.example.calculator.quotesapp.data.database.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class QuoteRoomModule {
    private val QUOTE_DATABASE_NAME = "quote_database"

    @Singleton
    @Provides
    fun provideQuoteRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QuoteDatabase::class.java, QUOTE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuoteDao(db:QuoteDatabase) = db.getQuoteDao()
}