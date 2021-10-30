package com.moriswala.booking.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.moriswala.booking.data.repository.BookingRepository

class CharactersViewModel @ViewModelInject constructor(
    private val repository: BookingRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    val bookings = repository.getAllBookings()

    fun auth() = auth

}
