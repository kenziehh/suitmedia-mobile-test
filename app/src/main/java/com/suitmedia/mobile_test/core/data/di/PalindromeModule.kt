package com.suitmedia.mobile_test.core.data.di

import com.suitmedia.mobile_test.domain.usecase.PalindromeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PalindromeModule {

    @Provides
    fun providePalindromeUseCase(): PalindromeUseCase {
        return PalindromeUseCase()
    }
}