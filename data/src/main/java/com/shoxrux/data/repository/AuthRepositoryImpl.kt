package com.shoxrux.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shoxrux.core.handler.NetworkResult
import com.shoxrux.data.utills.Constants
import com.shoxrux.domain.model.user.UserModel
import com.shoxrux.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun firebaseSignUp(userModel: UserModel): Flow<NetworkResult<Boolean>> {
        return flow {
            emit(NetworkResult.Loading())

            try {
                val isSuccess = withContext(Dispatchers.IO) {
                    auth.createUserWithEmailAndPassword(userModel.email!!, userModel.password!!)
                        .await().user != null
                }

                if (isSuccess) {
                    Log.d("TAGFireStore", "firebaseSignUp: ${auth.currentUser}")
                    val firebaseUser = auth.currentUser
                    firebaseUser?.let {
                        userModel.id = it.uid
                        firestore.collection(Constants.USERS)
                            .document(it.uid)
                            .set(userModel)
                            .await()
                    }
                    emit(NetworkResult.Success(true))
                } else {
                    emit(NetworkResult.Error("Oh, something went wrong"))
                }

            } catch (e: Exception) {
                emit(NetworkResult.Error(e.message ?: "Unknown error"))
            }
        }
    }


    override suspend fun firebaseSignIn(
        email: String,
        password: String
    ): Flow<NetworkResult<Boolean>> {
        return flow {
            emit(NetworkResult.Loading())

            try {
                val result = auth.signInWithEmailAndPassword(email, password).await()

                if (result.user != null) {
                    Log.d("TAGRepository", "firebaseSignIn: success")
                    emit(NetworkResult.Success(true))
                } else {
                    Log.d("TAGRepository", "firebaseSignIn: failure")
                    emit(NetworkResult.Error("Invalid credentials"))
                }

            } catch (e: Exception) {
                emit(NetworkResult.Error(e.message ?: "Unknown error"))
            }
        }
    }
}