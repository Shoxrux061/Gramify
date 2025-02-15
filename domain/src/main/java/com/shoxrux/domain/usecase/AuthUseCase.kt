package com.shoxrux.domain.usecase

import com.shoxrux.domain.model.user.UserModel
import com.shoxrux.domain.repository.AuthRepository
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend fun signUpByEmail(userModel: UserModel) =
        repository.firebaseSignUp(userModel)

    suspend fun signInByEmail(email: String, password: String) =
        repository.firebaseSignIn(email, password)

}