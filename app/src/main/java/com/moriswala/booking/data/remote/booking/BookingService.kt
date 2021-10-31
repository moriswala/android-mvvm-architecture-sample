package com.moriswala.booking.data.remote.booking

import com.moriswala.booking.data.entities.Booking
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookingService {
    @GET("bookings")
    suspend fun getAllBookings() : Response<List<Booking>>

    @GET("bookings/{id}")
    suspend fun getBooking(@Path("id") id: Int): Response<Booking>

}