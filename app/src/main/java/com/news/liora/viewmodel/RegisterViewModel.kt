package com.news.liora.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.news.liora.data.User
import com.news.liora.utils.RegisterFieldState
import com.news.liora.utils.RegistrationValidation
import com.news.liora.utils.Resource
import com.news.liora.utils.validatePassword
import com.news.liora.utils.validationEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val firebaseAuth:FirebaseAuth): ViewModel() {

    private val _register = MutableStateFlow<Resource<FirebaseUser>>(Resource.Unspecified())
    val register: Flow<Resource<FirebaseUser>> = _register

    private val _validation = Channel<RegisterFieldState>()
    val validation = _validation.receiveAsFlow()

    fun accountWithEmailAndPassword(user:User,password:String){

        if (chakeValidation(user, password)){
            runBlocking {
                _register.emit(Resource.Loading())
            }

            firebaseAuth.createUserWithEmailAndPassword(user.email,password)
                .addOnSuccessListener {
                    it.user?.let {
                        _register.value = Resource.Success(it)
                    }
                }.addOnFailureListener {
                    _register.value = Resource.Error(it.message.toString())
                }
        }else{
            val registerFieldState = RegisterFieldState(validationEmail(user.email),
                validatePassword(password)
            )
            runBlocking {
                _validation.send(registerFieldState)
            }

        }

    }

    private fun chakeValidation(user: User, password: String):Boolean {
        val validateEmail = validationEmail(user.email)
        val validatePassword = validatePassword(password)
        val shouldRegister = validateEmail is RegistrationValidation.Success &&
                validatePassword is RegistrationValidation.Success

        return shouldRegister
    }

}