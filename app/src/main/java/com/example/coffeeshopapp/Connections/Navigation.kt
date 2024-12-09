package com.example.coffeeshopapp.Connections

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coffeeshopapp.Screens.GetStartedScreen
import com.example.coffeeshopapp.Screens.LoginScreen
import com.example.coffeeshopapp.Screens.SignUpScreen

@Composable
fun Navigation(navController : NavHostController){

    NavHost(navController = navController, startDestination = Screen.getStartedScreen.route){

        composable(route =Screen.getStartedScreen.route){
            GetStartedScreen(navController)

        }

        composable(route = Screen.loginScreen.route){
            LoginScreen(navController)
        }
        composable(route = Screen.loginScreen.route){
            SignUpScreen(navController)
        }



    }

}