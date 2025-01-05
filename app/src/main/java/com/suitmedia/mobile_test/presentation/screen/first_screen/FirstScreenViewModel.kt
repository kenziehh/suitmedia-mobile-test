package com.suitmedia.mobile_test.presentation.screen.first_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suitmedia.mobile_test.domain.usecase.PalindromeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FirstScreenViewModel(private val palindromeUseCase: PalindromeUseCase) : ViewModel() {

    private val _isPalindromeResult = MutableStateFlow<String?>(null)
    val isPalindromeResult: StateFlow<String?> get() = _isPalindromeResult

    private val _dialogVisible = MutableStateFlow(false)
    val dialogVisible: StateFlow<Boolean> get() = _dialogVisible

    fun checkPalindrome(input: String) {
        val result = if (palindromeUseCase.isPalindrome(input)) {
            "isPalindrome"
        } else {
            "not palindrome"
        }
        _isPalindromeResult.value = result
        _dialogVisible.value = true
    }

    fun dismissDialog() {
        _dialogVisible.value = false
    }
}
