package com.example.coffeeshopapp.Screens

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.coffeeshopapp.Connections.Screen
import com.example.coffeeshopapp.R
import com.example.coffeeshopapp.ui.theme.login
import com.example.coffeeshopapp.viewModel.UserViewModel
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController,viewModel: UserViewModel) {
    val authState = viewModel.authState.collectAsState()
    val errorMessage = viewModel.errorMessage.collectAsState()
    val loading = viewModel.loadingState.collectAsState()

    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var showModal by remember { mutableStateOf(false) }

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
                        .padding(bottom = 25.dp, top = 80.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        painter =painterResource(R.drawable.coffecupp),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(320.dp)
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            placeholder = {
                                Text("Enter Your Name.....")
                            },
                            label = {
                                Text(
                                    "Name",
                                    fontSize = 15.sp,
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                                    modifier = Modifier.padding(start = 12.dp)
                                )
                            },
                            leadingIcon = {
                                Image(
                                    painter = painterResource(R.drawable.user),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(18.dp)
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

                        OutlinedTextField(
                            value = selectedDate?.let { convertMillisToDate(it) } ?: "",
                            onValueChange = { },
                            label = {
                                Text(
                                    "DOB",
                                    fontSize = 15.sp,
                                    style = TextStyle(
                                        fontWeight = FontWeight.SemiBold,
                                    ),
                                    modifier = Modifier.padding(start = 12.dp)
                                )
                            },
                            placeholder = { Text("MM/DD/YYYY") },
                            trailingIcon = {
                                Icon(Icons.Default.DateRange, contentDescription = "Select date")
                            },
                            leadingIcon = {
                                Image(
                                    painter = painterResource(R.drawable.calendar),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(20.dp)
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
                                .pointerInput(selectedDate) {
                                    awaitEachGesture {
                                        // Modifier.clickable doesn't work for text fields, so we use Modifier.pointerInput
                                        // in the Initial pass to observe events before the text field consumes them
                                        // in the Main pass.
                                        awaitFirstDown(pass = PointerEventPass.Initial)
                                        val upEvent = waitForUpOrCancellation(pass = PointerEventPass.Initial)
                                        if (upEvent != null) {
                                            showModal = true
                                        }
                                    }
                                }
                        )

                        if (showModal) {
                            DatePickerModal(
                                onDateSelected = { selectedDate = it },
                                onDismiss = { showModal = false }
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
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
                Spacer(modifier = Modifier.height(30.dp))
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Button(
                        onClick = {
                            viewModel.registerUser(name,email,password,selectedDate!!)
                        },
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
                            text = "Sign Up",
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
                        Text("Already Registered?",
                            color = Color(0xFFD4AF37),
                            style = TextStyle(
                                fontSize = 18.sp
                            ),
                            fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Login",
                            modifier = Modifier.clickable(onClick = {navController.navigate(Screen.loginScreen.route)}))
                    }

                }
            }
        }
    }
    if(loading.value){
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFFEAE4D0)),
            contentAlignment = Alignment.Center){

            CircularProgressIndicator()

        }
    }
    if(authState.value.isSuccess){
        val user = authState.value.getOrNull()
        user?.let {
            navController.navigate(Screen.homeScreen.route)
        }
    }else {
        val error = authState.value.exceptionOrNull()
        errorMessage?.let {
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomCenter){
                Text("Error: $it", color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(bottom = 16.dp))
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        colors = DatePickerDefaults.colors(
            containerColor = Color(0xFFEAE4D0),
            titleContentColor = Color.Black
        ),
        confirmButton = {
            Button(
                onClick = {
                    val selectedDateMillis = datePickerState.selectedDateMillis
                    if (selectedDateMillis != null) {
                        onDateSelected(selectedDateMillis)
                    }
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}