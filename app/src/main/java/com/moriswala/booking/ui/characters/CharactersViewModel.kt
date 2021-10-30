package com.moriswala.booking.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.moriswala.booking.data.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Singleton

class CharactersViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    val characters = repository.getCharacters()

    fun auth() = auth

}
