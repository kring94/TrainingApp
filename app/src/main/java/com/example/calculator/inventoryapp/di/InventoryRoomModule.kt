package com.example.calculator.inventoryapp.di

import android.content.Context
import androidx.room.Room
import com.example.calculator.inventoryapp.data.ItemRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//@Module
//@InstallIn(SingletonComponent::class)
//object InventoryRoomModule {
//
//    private const val ITEM_DATABASE_NAME = "item_database"
//
//    @Singleton
//    @Provides
//    fun provideRoom(@ApplicationContext context: Context) =
//        Room.databaseBuilder(context, ItemRoomDatabase::class.java, ITEM_DATABASE_NAME).build()
//
//    @Singleton
//    @Provides
//    fun provideItemDao(db:ItemRoomDatabase) = db.itemDao()
//}