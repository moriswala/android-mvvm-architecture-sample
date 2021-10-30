package com.moriswala.booking.ui.auth.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel @ViewModelInject constructor(
    public val auth: FirebaseAuth
) : ViewModel() {

    fun auth() = auth

}
