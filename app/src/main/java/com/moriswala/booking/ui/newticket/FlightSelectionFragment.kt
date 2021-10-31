package com.moriswala.booking.ui.newticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.databinding.FragmentFlightSelectionBinding
import com.moriswala.booking.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightSelectionFragment : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_flight_selection

    private var binding: FragmentFlightSelectionBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentFlightSelectionBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarColor(R.color.colorPrimary, R.color.color_master_light)
        binding.cardView.setOnClickListener { view: View? ->
            findNavController().navigate(R.id.action_flightSelectionFragment_to_seatSelectionFragment)
        }
    }
}