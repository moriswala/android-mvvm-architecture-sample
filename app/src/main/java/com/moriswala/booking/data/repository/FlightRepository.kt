package com.moriswala.booking.data.repository

import com.moriswala.booking.data.local.BookingDao
import com.moriswala.booking.data.local.FlightDao
import com.moriswala.booking.data.remote.booking.FlightRemoteDataSource
import com.moriswala.booking.utils.performGetOperation
import javax.inject.Inject

class FlightRepository @Inject constructor(
    private val remoteDataSource: FlightRemoteDataSource,
    private val localDataSource: FlightDao
) {

    fun getFlight(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getFlight(id) },
        networkCall = { remoteDataSource.getFlight(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getAllFlights() = performGetOperation(
        databaseQuery = { localDataSource.getAllFlights() },
        networkCall = { remoteDataSource.getAllFlights() },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}