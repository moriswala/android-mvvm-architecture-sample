package com.moriswala.booking.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.moriswala.booking.data.local.AppDatabase
import com.moriswala.booking.data.local.BookingDao
import com.moriswala.booking.data.remote.booking.FlightRemoteDataSource
import com.moriswala.booking.data.repository.BookingRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.moriswala.booking.data.local.FlightDao
import com.moriswala.booking.data.remote.booking.BookingRemoteDataSource
import com.moriswala.booking.data.remote.booking.BookingService
import com.moriswala.booking.data.remote.booking.FlightService
import com.moriswala.booking.data.repository.FlightRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://my-booking-project.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideBookingService(retrofit: Retrofit): BookingService = retrofit.create(BookingService::class.java)

    @Singleton
    @Provides
    fun provideBookingRemoteDataSource(bookingService: BookingService) = BookingRemoteDataSource(bookingService)

    @Provides
    fun provideFlightService(retrofit: Retrofit): FlightService = retrofit.create(FlightService::class.java)

    @Singleton
    @Provides
    fun provideFlightRemoteDataSource(flightService: FlightService) = FlightRemoteDataSource(flightService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideBookingDao(db: AppDatabase) = db.bookingDao()

    @Singleton
    @Provides
    fun provideFlightDao(db: AppDatabase) = db.flightDao()

    @Singleton
    @Provides
    fun provideCityDao(db: AppDatabase) = db.cityDao()

    @Singleton
    @Provides
    fun provideBookingRepository(remoteDataSource: BookingRemoteDataSource,
                          localDataSource: BookingDao) =
        BookingRepository(remoteDataSource, localDataSource)

    @Singleton
    @Provides
    fun provideFlightRepository(remoteDataSource: FlightRemoteDataSource,
                          localDataSource: FlightDao) =
        FlightRepository(remoteDataSource, localDataSource)
}