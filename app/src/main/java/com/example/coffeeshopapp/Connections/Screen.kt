package com.example.coffeeshopapp.Connections

import androidx.annotation.DrawableRes

sealed class Screen(val title : String, val route : String) {

    object getStartedScreen : Screen("Start Screen", "getStartedScreen")
    object signUpScreen : Screen("SignUp Screen", "SignUpScreen")
    object loginScreen : Screen("loginScreen", "loginScreen")


        sealed class BottomNavScreen(val bTitle: String,
                                  val bRoute: String,
                                  @DrawableRes val icon:Int) : Screen(bTitle,bRoute) {

        }

}