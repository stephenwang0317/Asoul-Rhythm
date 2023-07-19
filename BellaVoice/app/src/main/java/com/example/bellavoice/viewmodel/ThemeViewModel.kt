package com.example.bellavoice.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bellavoice.model.datastore.ThemeInfoManager
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class ThemeViewModel(context: Context) : ViewModel() {

    var themeId by mutableStateOf(1)
    private val themeInfoManager = ThemeInfoManager(context)

    fun changeTheme(id: Int) {
        themeId = id
        save(id)
    }

    init {
        viewModelScope.launch {
            themeId = themeInfoManager.id.firstOrNull() ?: 1
        }
    }

    private fun save(id: Int) {
        viewModelScope.launch { themeInfoManager.save(id) }
    }
}