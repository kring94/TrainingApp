package com.example.calculator.busscheduleapp

import android.app.Application
import com.example.calculator.busscheduleapp.database.AppDatabase

class BusScheduleApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}