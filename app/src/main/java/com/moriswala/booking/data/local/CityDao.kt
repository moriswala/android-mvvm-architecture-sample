package com.moriswala.booking.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moriswala.booking.data.entities.Booking
import com.moriswala.booking.data.entities.City

@Dao
interface CityDao {

    @Query("SELECT * FROM cities")
    fun getAllCities() : LiveData<List<City>>

    @Query("SELECT * FROM cities WHERE id = :id")
    fun getCity(id: Int): LiveData<City>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities: List<City>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: City)


}