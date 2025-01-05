package com.suitmedia.mobile_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.suitmedia.mobile_test.domain.usecase.PalindromeUseCase
import com.suitmedia.mobile_test.presentation.design_system.Mobile_testTheme
import com.suitmedia.mobile_test.presentation.screen.first_screen.FirstScreen
import com.suitmedia.mobile_test.presentation.screen.first_screen.FirstScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mobile_testTheme {
                FirstScreen(FirstScreenViewModel(PalindromeUseCase()))
            }
        }
    }
}

