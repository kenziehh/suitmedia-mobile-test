package com.suitmedia.mobile_test.presentation.screen.second_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suitmedia.mobile_test.core.data.local.preference.UserPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondScreenViewModel @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager
) : ViewModel() {

    private val _username = MutableStateFlow<String?>("Guest")
    val username: StateFlow<String?> = _username.asStateFlow()

    private val _selectedUsername = MutableStateFlow<String?>("There are no selected users")
    val selectedUsername: StateFlow<String?> = _selectedUsername.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferencesManager.username.collect { name ->
                _username.value = name ?: "Guest"
            }
        }

        viewModelScope.launch {
            userPreferencesManager.selectedUsername.collect { selectedName ->
                _selectedUsername.value =
                    selectedName ?: "There are no selected users"
            }
        }
    }

    fun saveSelectedUsername(selectedUsername: String) {
        viewModelScope.launch {
            userPreferencesManager.saveSelectedUsername(selectedUsername)
        }
    }
}