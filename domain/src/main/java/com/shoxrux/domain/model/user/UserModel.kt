package com.shoxrux.domain.model.user

data class UserModel(

    var id: String? = "",
    var email: String? = "",
    var password: String? = "",
    var fullName: String? = "",
    var bio: String? = "",
    var gender: Int? = 1,
    var phoneNumber: String? = "",
    var dateOfBirth: String? = ""

)
