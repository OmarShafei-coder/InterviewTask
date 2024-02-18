package com.example.interviewtask

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.interviewtask.databinding.FragmentMapBinding
import com.example.interviewtask.services.LocationService
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView


class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private lateinit var serviceIntent: Intent
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        initMapView()
        serviceIntent = Intent(requireContext(), LocationService::class.java)

        binding.button.setOnClickListener {
            activity?.startService(serviceIntent)
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
        mapView.controller.setZoom(2.5)
    }
    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
        activity?.stopService(serviceIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}