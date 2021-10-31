package com.moriswala.booking.ui.newticket.flightselection

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.moriswala.booking.data.repository.FlightRepository

class FlightSelectionViewModel @ViewModelInject constructor(
    private val repository: FlightRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    val flights = repository.getAllFlights()

    fun auth() = auth

}
