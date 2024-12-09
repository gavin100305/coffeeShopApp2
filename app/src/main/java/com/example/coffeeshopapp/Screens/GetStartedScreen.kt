package com.example.coffeeshopapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.coffeeshopapp.Connections.Screen
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.ui.theme.buttonColor
import com.example.coffeeshopapp.ui.theme.orange8
import com.example.coffeeshopapp.ui.theme.orange9


@Composable
fun GetStartedScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.peakpx),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Text Content
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 370.dp)
        ) {
            Text(
                text = "Choose Your\nFavorite Coffee",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(
                    fontSize = 30.sp,
                    lineHeight = 35.sp
                ),
                modifier = Modifier
                    .padding(vertical = 25.dp)
                    .fillMaxWidth(),
            )

            Text(
                text = "The best grain, the finest roast, the most powerful flavour",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

        }
        Button(onClick = {navController.navigate(Screen.loginScreen.route)}, colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = Color.Black
        ),modifier = Modifier
            .align(Alignment.BottomCenter).padding(50.dp) ) {

            Text("Get Started",
                modifier = Modifier.padding(horizontal = 50.dp),
                style = TextStyle(
                    fontSize = 20.sp
                ))
        }

        Text("THE COFFEESHOP APP",
            modifier = Modifier.align(Alignment.TopCenter).padding(vertical = 100.dp, horizontal = 20.dp), color = Color.White,
            style = TextStyle(
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 60.sp
            ))
    }
}

