package com.moriswala.booking.ui.auth.resetpassword

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class ResetPasswordViewModel @ViewModelInject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {

    fun auth() = auth

}
