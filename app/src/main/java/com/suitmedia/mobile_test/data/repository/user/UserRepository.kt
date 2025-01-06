package com.suitmedia.mobile_test.data.repository.user

import com.suitmedia.mobile_test.core.models.UserRegres
import com.suitmedia.mobile_test.data.remote.user.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {
    suspend fun getUsers(page: Int, perPage: Int): Result<List<UserRegres>> {
        return try {
            val response = userService.getUsers(page, perPage)
            if (response.isSuccessful) {
                Result.success(response.body()?.data ?: emptyList())
            } else {
                Result.failure(Exception("Error: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}