package com.news.liora.utils

sealed class RegistrationValidation {
    object Success:RegistrationValidation()
    data class Faild(val message:String):RegistrationValidation()
}

data class RegisterFieldState(
    val email:RegistrationValidation,
    val password:RegistrationValidation
)