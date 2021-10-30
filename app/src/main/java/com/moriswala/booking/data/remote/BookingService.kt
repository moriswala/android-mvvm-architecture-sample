package com.moriswala.booking.data.remote

import com.moriswala.booking.data.entities.Booking
import com.moriswala.booking.data.entities.BookingList
import com.moriswala.booking.data.entities.City
import com.moriswala.booking.data.entities.CityList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookingService {
    @GET("bookings")
    suspend fun getAllBookings() : Response<BookingList>

    @GET("bookings/{id}")
    suspend fun getBooking(@Path("id") id: Int): Response<Booking>

}