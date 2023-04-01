package com.example.calculator

import android.app.Application
import com.example.calculator.busscheduleapp.database.AppDatabase
import com.example.calculator.inventoryapp.data.ItemRoomDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    val databaseInventory: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this)}
}