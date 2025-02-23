package com.shoxrux.presentation.screens.auth.signUp

data class SignUpModel(
    val fullName: String? = null,
    val email: String? = null,
    val dateOfBirth: String? = null,
    val gender: Int? = null,
    val about: String? = null
)