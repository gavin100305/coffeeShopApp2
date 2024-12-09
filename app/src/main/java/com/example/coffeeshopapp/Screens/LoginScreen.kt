package com.example.coffeeshopapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffeeshopapp.Connections.Screen
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.ui.theme.buttonColor
import com.example.coffeeshopapp.ui.theme.login


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember {mutableStateOf("")}
    var loading by remember {mutableStateOf(false)}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(login),
    ) {
        Box(modifier = Modifier) {
            Column(modifier = Modifier.align(Alignment.TopCenter)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 50.dp, top = 80.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter =painterResource(R.drawable.coffecupp),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(320.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        // Email TextField
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = {
                                Text("Enter Your Email.....")
                            },
                            label = {
                                Text(
                                    "Email",
                                    fontSize = 15.sp,
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                                    modifier = Modifier.padding(start = 12.dp)
                                )
                            },
                            leadingIcon = {
                                Image(
                                    painter = painterResource(R.drawable.email),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(16.dp)
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent
                            ),
                            modifier = Modifier
                                .clip(RoundedCornerShape(30.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(30.dp)
                                )
                                .background(color = Color(0xFFEAE4D0))
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Password TextField
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            placeholder = {
                                Text("Enter Your Password.....")
                            },
                            label = {
                                Text(
                                    "Password",
                                    fontSize = 15.sp,
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                                    modifier = Modifier.padding(start = 12.dp)
                                )
                            },
                            leadingIcon = {
                                Image(
                                    painter = painterResource(R.drawable.password),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color.Transparent,
                                focusedBorderColor = Color.Transparent,
                            ),
                            modifier = Modifier
                                .clip(RoundedCornerShape(30.dp))
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    shape = RoundedCornerShape(30.dp)
                                )
                                .background(color = Color(0xFFEAE4D0))
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                    }
                }
                Spacer(modifier = Modifier.height(70.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Button(
                        onClick = {  },
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier
                            .height(60.dp)
                            .fillMaxWidth(0.45f)
                            .shadow(4.dp, RoundedCornerShape(15.dp)),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        )
                    ) {
                        Text(
                            text = "Sign In",
                            color = Color(0xFFD4AF37),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }


                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .align(Alignment.Center)
                            .offset(x = 86.dp)
                            .shadow(4.dp, CircleShape)
                            .background(Color(0xFFD4AF37), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.coffeecupppp),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
               Box(Modifier.fillMaxWidth(),
                   contentAlignment = Alignment.Center){

                   Row(){
                       Text("Not Registered?",
                           color = Color(0xFFD4AF37),
                           style = TextStyle(
                               fontSize = 18.sp
                           ),
                           fontWeight = FontWeight.Bold)
                       Spacer(modifier = Modifier.width(3.dp))
                       Text("Sign Up",
                           modifier = Modifier.clickable(onClick = {navController.navigate(Screen.signUpScreen.route)}))
                   }

               }
            }
        }
    }
}