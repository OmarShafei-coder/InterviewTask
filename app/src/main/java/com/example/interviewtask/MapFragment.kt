package com.example.interviewtask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.interviewtask.databinding.FragmentMapBinding
import org.osmdroid.config.Configuration
import org.osmdroid.views.MapView


class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)

        // Initialize OSMDroid configuration
        Configuration.getInstance().load(context, activity?.getPreferences(AppCompatActivity.MODE_PRIVATE))

        mapView = binding.mapView

        mapView.isHorizontalMapRepetitionEnabled = false
        mapView.isVerticalMapRepetitionEnabled = false

        mapView.setMultiTouchControls(true)

        mapView.controller.setZoom(2.5)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}