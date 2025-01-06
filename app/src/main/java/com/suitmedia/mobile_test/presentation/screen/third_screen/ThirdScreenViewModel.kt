package com.suitmedia.mobile_test.presentation.screen.third_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suitmedia.mobile_test.core.data.local.preference.UserPreferencesManager
import com.suitmedia.mobile_test.core.models.UserRegres
import com.suitmedia.mobile_test.data.remote.user.UserService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdScreenViewModel @Inject constructor(
    private val apiService: UserService,
    private val preferencesManager: UserPreferencesManager
) : ViewModel() {

    private val _users = MutableStateFlow<List<UserRegres>>(emptyList())
    val users: StateFlow<List<UserRegres>> = _users

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private var currentPage = 1
    private val perPage = 10

    init {
        fetchUsers()

    }

    fun fetchUsers() {
        viewModelScope.launch {
            _isRefreshing.value = true
            try {
                val response = apiService.getUsers(currentPage, perPage)
                if (response.isSuccessful) {
                    response.body()?.data?.let { userList ->
                        // Append fetched users to the existing list
                        _users.value = _users.value + userList

                        // Log the fetched user list
                        Log.d("ThirdScreenViewModel", "Fetched users: $userList")
                    }
                } else {
                    // Log the error response if not successful
                    Log.e("ThirdScreenViewModel", "Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                // Log the exception
                Log.e("ThirdScreenViewModel", "Exception occurred: ${e.message}", e)
            } finally {
                _isRefreshing.value = false
            }
        }
    }


    fun refreshUsers() {
        viewModelScope.launch {
            _users.value = emptyList()
            currentPage = 1
            fetchUsers()
        }
    }

    fun loadNextPage() {
        viewModelScope.launch {
            currentPage++
            fetchUsers()
        }
    }

    fun saveSelectedUsername(name: String) {
        viewModelScope.launch {
            preferencesManager.saveSelectedUsername(name)
        }
    }
}