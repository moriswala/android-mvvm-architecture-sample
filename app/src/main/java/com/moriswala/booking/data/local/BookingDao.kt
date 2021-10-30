package com.moriswala.booking.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moriswala.booking.data.entities.Booking

@Dao
interface BookingDao {

    @Query("SELECT * FROM bookings")
    fun getAllBookings() : LiveData<List<Booking>>

    @Query("SELECT * FROM bookings WHERE id = :id")
    fun getBooking(id: Int): LiveData<Booking>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(bookings: List<Booking>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(booking: Booking)


}