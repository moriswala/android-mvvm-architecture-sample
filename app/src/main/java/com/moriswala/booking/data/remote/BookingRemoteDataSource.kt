package com.moriswala.booking.data.remote

import javax.inject.Inject

class BookingRemoteDataSource @Inject constructor(
    private val bookingService: BookingService
): BaseDataSource() {

    suspend fun getAllBookings() = getResult { bookingService.getAllBookings() }
    suspend fun getBooking(id: Int) = getResult { bookingService.getBooking(id) }
}