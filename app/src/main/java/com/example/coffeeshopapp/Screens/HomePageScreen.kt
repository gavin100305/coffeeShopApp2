package com.example.coffeeshopapp.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(){
    Box(modifier = Modifier.fillMaxWidth()) {
        Text("Welcome to Home Screen")
    }
}