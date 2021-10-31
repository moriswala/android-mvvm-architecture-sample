package com.moriswala.booking.ui.newticket.flightselection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.moriswala.booking.base.BaseFragment
import com.moriswala.booking.R
import com.moriswala.booking.data.entities.Flight
import com.moriswala.booking.databinding.FragmentFlightSelectionBinding
import com.moriswala.booking.utils.Resource
import com.moriswala.booking.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightSelectionFragment : BaseFragment(), FlightsAdapter.ItemListener {

    override fun getLayoutResId(): Int = R.layout.fragment_flight_selection

    private lateinit var adapter: FlightsAdapter
    private var binding: FragmentFlightSelectionBinding by autoCleared()
    private val viewModel: FlightSelectionViewModel by viewModels()

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
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = FlightsAdapter(this)
        binding.flightsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.flightsRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.flights.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onFlightSelected(flight: Flight) {
//        getNavController().navigate(
//            R.id.action_charactersFragment_to_characterDetailFragment,
//            bundleOf("id" to bookingId)
//        )
        getNavController().navigate(R.id.action_flightSelectionFragment_to_seatSelectionFragment,
            bundleOf("flight" to JsonUtils.toJsonString(flight)))
    }
}