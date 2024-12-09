package com.example.coffeeshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.coffeeshopapp.Connections.Navigation
import com.example.coffeeshopapp.Screens.GetStartedScreen
import com.example.coffeeshopapp.Screens.LoginScreen
import com.example.coffeeshopapp.Screens.SignUpScreen
import com.example.coffeeshopapp.ui.theme.CoffeeShopAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeShopAppTheme {
                val navController = rememberNavController()
                Navigation(navController)

            }
        }
    }
}

