package com.example.interviewtask

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class LocationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        intent.action?.let {
            if (it == CustomReceiver.ACTION_LOCATION_BROADCAST.value)
                Toast.makeText(context, intent.getStringExtra("locationName").toString(), Toast.LENGTH_SHORT).show()
        }
    }
}