package com.example.interviewtask.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.example.interviewtask.utils.Enum
import com.example.interviewtask.models.LocationData
import com.example.interviewtask.ui.MainActivity
import com.example.interviewtask.viewmodels.SharedViewModel

class LocationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        intent.action?.let {
            if (it == Enum.ACTION_LOCATION_BROADCAST.value) {
                val receivedObject = intent.getSerializableExtra(Enum.LOCATION_DATA.value, LocationData::class.java)
                // Update the ViewModel
                receivedObject?.let {
                    val sharedViewModel = ViewModelProvider(context as MainActivity)[SharedViewModel::class.java]
                    sharedViewModel.updateLocation(receivedObject)
                }
            }
        }
    }
}