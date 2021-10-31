package com.moriswala.booking.data.remote.booking

import com.moriswala.booking.data.remote.BaseDataSource
import javax.inject.Inject

class BookingRemoteDataSource @Inject constructor(
    private val bookingService: BookingService
): BaseDataSource() {

    suspend fun getAllBookings() = getResult { bookingService.getAllBookings() }
    suspend fun getBooking(id: Int) = getResult { bookingService.getBooking(id) }
}