package com.suitmedia.mobile_test.core.data.di

import com.suitmedia.mobile_test.data.remote.user.UserService
import com.suitmedia.mobile_test.data.repository.user.UserRepository
import com.suitmedia.mobile_test.domain.usecase.GetUserUseCase
import com.suitmedia.mobile_test.domain.usecase.PalindromeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }
    @Provides
    @Singleton
    fun provideUserRepository(userService: UserService): UserRepository {
        return UserRepository(userService)
    }

    @Provides
    @Singleton
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }
}