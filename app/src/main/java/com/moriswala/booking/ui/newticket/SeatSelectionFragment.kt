package com.moriswala.booking.ui.newticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.data.entities.Flight
import com.moriswala.booking.databinding.FragmentSeatSelectionBinding
import com.moriswala.booking.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeatSelectionFragment : BaseFragment() {

    override fun getLayoutResId(): Int = R.layout.fragment_seat_selection

    private lateinit var flight: Flight
    private var binding: FragmentSeatSelectionBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentSeatSelectionBinding.bind(view)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarColor(R.color.colorPrimary, R.color.color_master_light)
        arguments?.getString("flight")?.let {
            flight = JsonUtils.fromJsonString(it, Flight::class.java)
            populateFlightDetails(flight)
        }

        binding.confirmSeatButton.setOnClickListener{
            findNavController().navigate(R.id.action_seatSelectionFragment_to_successFragment)
        }

    }

    private fun populateFlightDetails(flight: Flight) {
        binding.fromCity.text = flight.depart_city_id
        binding.toCity.text = flight.arrival_city_id
        binding.departTime.text = flight.depart_date_time
        binding.arrivalTime.text = flight.arrival_date_time
        binding.airlineName.text = flight.owner
        binding.priceText.text = "${getString(R.string.currency)} ${flight.fare}"
    }
}