package com.moriswala.booking.data.remote.booking

import com.moriswala.booking.data.remote.BaseDataSource
import javax.inject.Inject

class FlightRemoteDataSource @Inject constructor(
    private val flightService: FlightService
): BaseDataSource() {

    suspend fun getAllFlights() = getResult { flightService.getAllFlights() }
    suspend fun getFlight(id: Int) = getResult { flightService.getFlight(id) }
}