package com.example.interviewtask.models

import java.io.Serializable

data class LocationData(
    val latitude: Double? = null,
    val longitude: Double? = null,
    val locationName: String? = null
): Serializable // to be able to send this objet in an intent using putExtra function
