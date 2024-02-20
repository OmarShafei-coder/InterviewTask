package com.example.interviewtask.viewmodels

import androidx.lifecycle.ViewModel
import com.example.interviewtask.models.LocationData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel: ViewModel() {
    // Declare a private MutableStateFlow
    private val _locationData = MutableStateFlow(LocationData())
    // Declare a public StateFlow
    val locationData: StateFlow<LocationData> get() = _locationData

    // function to update the location data
    fun updateLocation(newLocationData: LocationData) {
        _locationData.value = newLocationData
    }
}