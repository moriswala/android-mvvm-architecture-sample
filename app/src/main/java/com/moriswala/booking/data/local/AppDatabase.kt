package com.moriswala.booking.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.moriswala.booking.data.entities.Booking
import com.moriswala.booking.data.entities.City
import com.moriswala.booking.data.entities.Flight

@Database(entities = [Booking::class, Flight::class, City::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bookingDao(): BookingDao

    abstract fun flightDao(): FlightDao

    abstract fun cityDao(): CityDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "characters")
                .fallbackToDestructiveMigration()
                .build()
    }

}