package com.example.interviewtask.services

import android.app.Service
import android.content.Intent
import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import android.os.IBinder
import android.util.Log
import com.example.interviewtask.Enum
import com.example.interviewtask.models.LocationData
import java.util.Locale
import java.util.Random
import java.util.Timer
import java.util.TimerTask


class LocationService : Service() {
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //send data each specific period
        scheduleSendingData()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun scheduleSendingData() {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                sendDataFromService()
            }
        }, 0, Enum.PERIOD.value.toLong())
    }

    private fun sendDataFromService() {
        val intent = Intent(Enum.ACTION_LOCATION_BROADCAST.value)
        val random = Random()
        val latitude = Enum.LATITUDE.value.toDouble() + (random.nextDouble() - 0.5) * 0.1 // Random latitude
        val longitude = Enum.LONGITUDE.value.toDouble() + (random.nextDouble() - 0.5) * 0.1 // Random longitude

        val listener = GeocodeListener { addresses ->
            if (addresses.isNotEmpty()) {
                val address = addresses[0]
                val locationName = address.locality ?: address.subAdminArea ?: address.adminArea
                //send location name
                intent.putExtra(Enum.LOCATION_DATA.value, LocationData(latitude, longitude, locationName))
                sendBroadcast(intent)
            } else {
                // Handle case where no address information is found or error occurred
                Log.e("LocationService", "Failed to geocode coordinates")
            }
        }
        val geocoder = Geocoder(applicationContext, Locale.getDefault())
        geocoder.getFromLocation(latitude, longitude, 1, listener)
    }
}