package com.suitmedia.mobile_test.domain.usecase

import com.suitmedia.mobile_test.core.models.UserRegres
import com.suitmedia.mobile_test.data.repository.user.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun execute(page: Int, perPage: Int): Result<List<UserRegres>> {
        return userRepository.getUsers(page, perPage)
    }
}