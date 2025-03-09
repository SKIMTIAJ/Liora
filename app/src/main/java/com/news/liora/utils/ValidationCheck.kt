package com.news.liora.utils

import android.util.Patterns
import org.intellij.lang.annotations.Pattern

fun validationEmail(email:String):RegistrationValidation{
    if (email.isEmpty())
        return RegistrationValidation.Faild("Email can't be empty")
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegistrationValidation.Faild("Wrong email")

    return RegistrationValidation.Success
}

fun validatePassword(password:String):RegistrationValidation{
    if (password.isEmpty())
        return RegistrationValidation.Faild("Password can't be empty")
    if(password.length<6)
        return RegistrationValidation.Faild("Password should be atleast 6 length")

    return RegistrationValidation.Success
}

fun validateMobileNumber(mobileNumber:String):RegistrationValidation{
    if (mobileNumber.length in 9..13)
        return RegistrationValidation.Success

    return RegistrationValidation.Faild("Password should be atleast 6 length")
}