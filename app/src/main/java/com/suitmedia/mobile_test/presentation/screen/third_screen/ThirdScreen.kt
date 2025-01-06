package com.suitmedia.mobile_test.presentation.screen.third_screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.suitmedia.mobile_test.R
import com.suitmedia.mobile_test.core.models.UserRegres
import com.suitmedia.mobile_test.presentation.component.CustomButton

@Composable
fun ThirdScreen(
    navController: NavController,
    viewModel: ThirdScreenViewModel = hiltViewModel()
) {
    val users by viewModel.users.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    Box(modifier = Modifier
        .fillMaxSize()
        .safeDrawingPadding()) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Text("Third Screen", style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold))
            Box(modifier = Modifier.size(46.dp))
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (users.isEmpty()) {
            Text(
                text = "No users found.",
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 16.sp
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(users) { user ->
                    UserListItem(
                        user = user,
                        onClick = {
                            // Save selected user to preferences
                            viewModel.saveSelectedUsername("${user.firstName} ${user.lastName}")
                            navController.popBackStack() // Navigate back to the Second Screen
                        }
                    )
                }
            }
        }
        CustomButton(
            onClick = { viewModel.refreshUsers() },
            text = "Pull to Refresh",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}

@Composable
fun UserListItem(
    user: UserRegres,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatar)
                    .crossfade(true)
                    .build()
            ),
            contentDescription = null,
            modifier = Modifier
                .size(56.dp)
                .background(color = Color.LightGray, shape = CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${user.firstName} ${user.lastName}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = user.email,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start
            )
        }
    }
}