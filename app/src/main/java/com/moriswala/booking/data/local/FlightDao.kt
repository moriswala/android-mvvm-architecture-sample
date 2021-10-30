package com.moriswala.booking.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moriswala.booking.data.entities.Flight

@Dao
interface FlightDao {

    @Query("SELECT * FROM flights")
    fun getAllFlights() : LiveData<List<Flight>>

    @Query("SELECT * FROM flights WHERE id = :id")
    fun getFlight(id: Int): LiveData<Flight>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(flights: List<Flight>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flight: Flight)


}