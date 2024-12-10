package com.example.coffeeshopapp.data
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class UserRepository(
    private val auth : FirebaseAuth = FirebaseAuth.getInstance(),
    private val database : FirebaseDatabase = FirebaseDatabase.getInstance()
) {
    suspend fun registerUser(user:User): Result<User>{
        return try{
            val authResult = auth.createUserWithEmailAndPassword(user.email!!,user.password!!).await()
            val uid = authResult.user?.uid?: throw Exception("uid is null")

            val userwithId = user.copy(uid = uid, password = null)
            database.reference.child("Users").child(uid).setValue(userwithId)

            Result.success(userwithId)
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    suspend fun loginUser(email : String, password : String) : Result<User>{
        return try {
            auth.signInWithEmailAndPassword(email,password).await()

            val uid = auth.currentUser?.uid ?: throw Exception("User ID is null")
            val snapshot = database.reference.child("Users").child(uid).get().await()
            val user = snapshot.getValue(User::class.java)?: throw Exception("user is null")

            Result.success(user)
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun currentUser(): Result<User>{
        return try {

            val uid = auth.currentUser?.uid?: throw Exception("User ID is null")
            val snapshot = database.reference.child("Users").child(uid).get().await()
            val user = snapshot.getValue(User ::class.java)?: throw Exception("user is null")

            Result.success(user)
        }catch(e : Exception){
            Result.failure(e)
        }
    }
}


