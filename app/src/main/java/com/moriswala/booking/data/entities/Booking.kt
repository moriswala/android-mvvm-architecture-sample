package com.moriswala.booking.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey
    val id: Int,
    val depart_city_id: String,
    val arrival_city_id: String,
    val depart_date_time: String,
    val arrival_date_time: String,
    val owner: String,
    val stops: Int,
    val fare: String,
    val currency: String,
    val logo: String,
    val status: Int
)