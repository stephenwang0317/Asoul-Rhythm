package com.example.bellavoice.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ThemeViewModel : ViewModel() {

    var themeId by mutableStateOf(1)

    fun changeTheme(id: Int) {
        themeId = id
    }

}