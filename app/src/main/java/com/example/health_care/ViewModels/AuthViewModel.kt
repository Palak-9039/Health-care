package com.example.health_care.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser: LiveData<FirebaseUser?> =_firebaseUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error :LiveData<String?> = _error


    fun register(email : String,
                 password : String,
                 name : String){
         _isLoading.value = true
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(it.isSuccessful){
                    _isLoading.value = false
                    _firebaseUser.value = auth.currentUser
                }else{
                    _error.value = it.exception?.message ?: "Registration failed"
                    Log.e("authViewmodel","registration failed ${it.exception?.message}")
                }
            }

    }

    fun login(email: String,password: String){
        _isLoading.value = true
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                _isLoading.value = false
                if (it.isSuccessful) {
                    _firebaseUser.value = auth.currentUser
                    Log.d("authViewModel", "login successful")
                } else {
                    _error.value = it.exception?.message ?: "Login failed"
                    Log.e("authViewModel", "login failed ${it.exception?.message}")
                }
            }

    }


    fun logout(){
        auth.signOut()
        _firebaseUser.value = null
    }



}