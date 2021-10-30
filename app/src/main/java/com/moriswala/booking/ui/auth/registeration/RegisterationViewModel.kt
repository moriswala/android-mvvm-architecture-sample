package com.moriswala.booking.ui.auth.registeration

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class RegisterationViewModel @ViewModelInject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {

    fun auth() = auth

}
