package com.news.liora.data

data class User(
    val firstName:String,
    val email:String,
    val mobileNumber:String="",
    val imagePath :String= ""
){
    constructor():this("","","","")
}
