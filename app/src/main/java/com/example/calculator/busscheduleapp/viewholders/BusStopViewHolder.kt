package com.example.calculator.busscheduleapp.viewholders

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.calculator.busscheduleapp.database.schedule.Schedule
import com.example.calculator.databinding.BusStopItemBinding
import java.text.SimpleDateFormat
import java.util.*

class BusStopViewHolder(private var binding: BusStopItemBinding): RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SimpleDateFormat")
    fun bind(schedule: Schedule) {
        binding.stopNameTextView.text = schedule.stopName
        binding.arrivalTimeTextView.text = SimpleDateFormat(
            "h:mm a").format(
            Date(schedule.arrivalTime.toLong() * 1000)
        )
    }
}