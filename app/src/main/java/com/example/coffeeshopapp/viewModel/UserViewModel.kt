package com.example.coffeeshopapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshopapp.data.User
import com.example.coffeeshopapp.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userState = MutableStateFlow<Result<User?>>(Result.success(null))
    val userState: StateFlow<Result<User?>> = _userState

    private val _authState = MutableStateFlow<Result<User?>>(Result.success(null))
    val authState: StateFlow<Result<User?>> = _authState

    private val _loadingState = MutableStateFlow(false)
    val loadingState : StateFlow<Boolean> = _loadingState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage : StateFlow<String?> = _errorMessage

    fun registerUser(name: String, email: String, password: String, dob: Long) {
        viewModelScope.launch {
            _errorMessage.value = null
            _loadingState.value = true
            val user = User(name = name, email = email, password = password, dob = dob)
            val result = repository.registerUser(user)
            _authState.value = result
            _loadingState.value = false

            if (result.isFailure) {
                _errorMessage.value = result.exceptionOrNull()?.message
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _errorMessage.value = null
            _loadingState.value = true
            val result = repository.loginUser(email, password)
            _authState.value = result
            _loadingState.value = false

            if (result.isFailure) {
                _errorMessage.value = result.exceptionOrNull()?.message
            }
        }
    }

    fun currentUser() {
        viewModelScope.launch {
            _loadingState.value = true
            _errorMessage.value = null
            val result = repository.currentUser()
            if (result.isSuccess) {
                _userState.value = result
            } else {
                _errorMessage.value = result.exceptionOrNull()?.message
            }
            _loadingState.value = false
        }
    }
}
