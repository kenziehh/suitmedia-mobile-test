package com.suitmedia.mobile_test.domain.usecase

class PalindromeUseCase {
    fun isPalindrome(input: String): Boolean {
        val sanitizedInput = input.replace("\\s".toRegex(), "").lowercase()
        return sanitizedInput == sanitizedInput.reversed()
    }
}
