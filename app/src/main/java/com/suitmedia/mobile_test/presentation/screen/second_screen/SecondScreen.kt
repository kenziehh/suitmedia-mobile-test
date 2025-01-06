package com.suitmedia.mobile_test.presentation.screen.second_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.suitmedia.mobile_test.presentation.component.CustomButton

@Composable
fun SecondScreen(
    navController: NavController,
    viewModel: SecondScreenViewModel = hiltViewModel()
) {
    val username by viewModel.username.collectAsState()
    val selectedUsername by viewModel.selectedUsername.collectAsState()
    Column(modifier = Modifier.safeDrawingPadding().fillMaxSize().padding(horizontal = 6.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text("Second Screen", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
            Box(modifier = Modifier.size(46.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column{
            Text(
                text = "Welcome",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Text(
                text = username ?: "Guest",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = selectedUsername ?: "There are no selected users",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                CustomButton(
                    onClick = {
                        navController.navigate("third_screen")
                    },
                    text = "Choose A User"
                )
            }
        }
    }

}