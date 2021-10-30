package com.moriswala.booking.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainViewModel @ViewModelInject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {

    fun auth() = auth
}
