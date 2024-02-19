package com.example.interviewtask.services

import android.app.Service
import android.content.Intent
import android.location.Geocoder
import android.os.IBinder
import android.util.Log
import com.example.interviewtask.CustomReceiver
import java.util.Locale
import java.util.Random
import java.util.Timer
import java.util.TimerTask

class LocationService : Service() {
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Simulate receiving data from the background service every 5 seconds
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                sendDataFromService()
            }
        }, 0, 5000)
        return super.onStartCommand(intent, flags, startId)
    }

    private fun sendDataFromService() {
        val intent = Intent(CustomReceiver.ACTION_LOCATION_BROADCAST.value)
        val random = Random()
        val latitude = 40.7128 + (random.nextDouble() - 0.5) * 0.1 // Random latitude
        val longitude = -74.0060 + (random.nextDouble() - 0.5) * 0.1 // Random longitude
        val locationName = getLocationName(latitude, longitude)
        locationName?.let {
            //send location name
            intent.putExtra("locationName", locationName)
            sendBroadcast(intent)
        }
    }

    private fun getLocationName(latitude: Double, longitude: Double): String? {
        var locationName: String? = null
        val geocoder = Geocoder(applicationContext, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)

        addresses?.let {
            if (addresses.isNotEmpty()) {
                val address = addresses[0]
                // cityName will contain the city name or a larger administrative area
                locationName = address.locality ?: address.subAdminArea ?: address.adminArea
            } else {
                // Handle case where no address information is found
                Log.e("MainActivity", "Wrong Coordinates")
            }
        }
        return locationName
    }
}