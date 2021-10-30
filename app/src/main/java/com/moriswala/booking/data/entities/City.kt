package com.moriswala.booking.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey
    val id: String,
    val nameEn: String,
    val nameAr: String
)