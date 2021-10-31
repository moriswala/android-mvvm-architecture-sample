package com.moriswala.booking.data.repository

import com.moriswala.booking.data.local.BookingDao
import com.moriswala.booking.data.remote.booking.BookingRemoteDataSource
import com.moriswala.booking.data.remote.booking.FlightRemoteDataSource
import com.moriswala.booking.utils.performGetOperation
import javax.inject.Inject

class BookingRepository @Inject constructor(
    private val remoteDataSource: BookingRemoteDataSource,
    private val localDataSource: BookingDao
) {

    fun getBooking(id: Int) = performGetOperation(
        databaseQuery = { localDataSource.getBooking(id) },
        networkCall = { remoteDataSource.getBooking(id) },
        saveCallResult = { localDataSource.insert(it) }
    )

    fun getAllBookings() = performGetOperation(
        databaseQuery = { localDataSource.getAllBookings() },
        networkCall = { remoteDataSource.getAllBookings() },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}