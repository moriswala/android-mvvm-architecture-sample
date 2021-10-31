package com.moriswala.booking.data.remote.booking

import com.moriswala.booking.data.entities.Booking
import com.moriswala.booking.data.entities.Flight
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FlightService {
    @GET("flights")
    suspend fun getAllFlights() : Response<List<Flight>>

    @GET("flight/{id}")
    suspend fun getFlight(@Path("id") id: Int): Response<Flight>

}