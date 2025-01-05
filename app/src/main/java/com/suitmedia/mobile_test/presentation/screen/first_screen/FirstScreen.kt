package com.suitmedia.mobile_test.presentation.screen.first_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.suitmedia.mobile_test.R
import androidx.compose.runtime.collectAsState
import com.suitmedia.mobile_test.presentation.component.CustomButton
import com.suitmedia.mobile_test.presentation.component.CustomTextField

@Composable
fun FirstScreen(
    viewModel: FirstScreenViewModel
) {
    val dialogVisible by viewModel.dialogVisible.collectAsState()
    val isPalindromeResult by viewModel.isPalindromeResult.collectAsState()

    var name by remember { mutableStateOf("") }
    var inputText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = "Name" ,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder =  "Palindrome" ,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                onClick = { viewModel.checkPalindrome(inputText) },
                modifier = Modifier.fillMaxWidth(),
                text = "CHECK"
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                onClick = {  },
                modifier = Modifier.fillMaxWidth(),
                text = "NEXT"

            )
        }
    }

    // Dialog for Palindrome Result
    if (dialogVisible) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissDialog() },
            title = { Text("Result") },
            text = { Text(isPalindromeResult ?: "") },
            confirmButton = {
                Button(onClick = { viewModel.dismissDialog() }) {
                    Text("OK")
                }
            }
        )
    }
}
