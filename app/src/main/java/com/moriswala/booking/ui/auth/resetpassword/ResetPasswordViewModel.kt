package com.moriswala.booking.ui.auth.resetpassword

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.moriswala.booking.data.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Singleton

class ResetPasswordViewModel @ViewModelInject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {

    fun auth() = auth

}
