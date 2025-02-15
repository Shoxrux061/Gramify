package com.shoxrux.domain.repository

import com.shoxrux.core.handler.NetworkResult
import com.shoxrux.domain.model.user.UserModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun firebaseSignUp(userModel: UserModel): Flow<NetworkResult<Boolean>>
    suspend fun firebaseSignIn(email: String, password: String): Flow<NetworkResult<Boolean>>

}