package com.moriswala.booking.ui.characterdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.moriswala.booking.data.entities.Booking
import com.moriswala.booking.data.repository.BookingRepository
import com.moriswala.booking.utils.Resource

class CharacterDetailViewModel @ViewModelInject constructor(
    private val repository: BookingRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _booking = _id.switchMap { id ->
        repository.getBooking(id)
    }
    val booking: LiveData<Resource<Booking>> = _booking


    fun start(id: Int) {
        _id.value = id
    }

}
