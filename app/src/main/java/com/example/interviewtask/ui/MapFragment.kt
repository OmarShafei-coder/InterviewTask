package com.example.interviewtask.ui

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.interviewtask.Enum
import com.example.interviewtask.LocationReceiver
import com.example.interviewtask.SharedViewModel
import com.example.interviewtask.databinding.FragmentMapBinding
import com.example.interviewtask.models.LocationData
import com.example.interviewtask.services.LocationService
import kotlinx.coroutines.launch
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MapFragment : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var serviceIntent: Intent
    private lateinit var mapView: MapView
    private lateinit var locationReceiver: LocationReceiver
    private lateinit var filter: IntentFilter
    private var _binding: FragmentMapBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        // Initialize ViewModel
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        locationReceiver = LocationReceiver()
        serviceIntent = Intent(requireContext(), LocationService::class.java)
        filter = IntentFilter(Enum.ACTION_LOCATION_BROADCAST.value)
        initMapView()

        binding.button.setOnClickListener {
            activity?.startService(serviceIntent)
        }

        lifecycleScope.launch {
            sharedViewModel.locationData.collect {
                if(it.locationName != null)
                    addMarkerOnMap(it)
            }
        }
        return binding.root
    }

    private fun initMapView() {
        // Initialize OSMDroid configuration
        Configuration.getInstance().load(context, activity?.getPreferences(AppCompatActivity.MODE_PRIVATE))
        mapView = binding.mapView
        mapView.isHorizontalMapRepetitionEnabled = false
        mapView.isVerticalMapRepetitionEnabled = false
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(Enum.MAP_ZOOM.value.toDouble())
    }

    private fun addMarkerOnMap(location: LocationData) {
        location.apply {
            val marker = Marker(mapView)
            marker.position = GeoPoint(latitude!!, longitude!!)
            marker.title = locationName
            mapView.overlays.add(marker)
            mapView.invalidate()
            Toast.makeText(context, locationName, Toast.LENGTH_SHORT).show()
        }
    }
    override fun onResume() {
        super.onResume()
        mapView.onResume()
        activity?.registerReceiver(locationReceiver, filter, Context.RECEIVER_EXPORTED)
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
        activity?.stopService(serviceIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        activity?.unregisterReceiver(locationReceiver)
    }
}