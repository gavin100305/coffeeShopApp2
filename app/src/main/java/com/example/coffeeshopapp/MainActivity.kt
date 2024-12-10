package com.example.coffeeshopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.coffeeshopapp.Connections.Navigation
import com.example.coffeeshopapp.data.UserRepository
import com.example.coffeeshopapp.ui.theme.CoffeeShopAppTheme
import com.example.coffeeshopapp.viewModel.UserViewModel
import com.example.coffeeshopapp.viewModel.UserViewModelFactory
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var userRepository: UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeShopAppTheme {
                FirebaseApp.initializeApp(this)
                userRepository = UserRepository()
                val navController = rememberNavController()
                viewModel = ViewModelProvider(
                    this,
                    UserViewModelFactory(userRepository)  // Pass the repository instance here
                ).get(UserViewModel::class.java)
                Navigation(navController,viewModel)

            }
        }
    }
}

