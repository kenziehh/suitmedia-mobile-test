package com.suitmedia.mobile_test.data.repository.user

import com.suitmedia.mobile_test.core.models.UserRegres
import com.suitmedia.mobile_test.data.remote.user.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {
    suspend fun getUsers(): Result<List<UserRegres>> {
        return try {
            val response = userService.getUsers(page = 1, perPage = 10)

            Result.success(
                response.body()?.data ?: throw Exception("Response body is null")
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}