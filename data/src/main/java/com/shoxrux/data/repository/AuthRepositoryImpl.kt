package com.shoxrux.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.shoxrux.core.handler.NetworkResult
import com.shoxrux.data.utills.Constants
import com.shoxrux.domain.model.user.UserModel
import com.shoxrux.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {

    override suspend fun firebaseSignUp(userModel: UserModel): Flow<NetworkResult<Boolean>> {
        return flow {

            var isSuccess = false

            emit(NetworkResult.Loading())

            try {

                auth.createUserWithEmailAndPassword(userModel.email, userModel.password)
                    .addOnCompleteListener { task ->
                        isSuccess = if (task.isSuccessful) {
                            Log.d("TAGRepository", "firebaseSignUp: success")
                            val firebaseUser = auth.currentUser
                            if (firebaseUser != null) {

                                firestore
                                    .collection(Constants.USERS)
                                    .document(firebaseUser.uid)
                                    .set(userModel)

                                userModel.id = firebaseUser.uid
                            }
                            firebaseUser != null
                        } else {
                            Log.d("TAGRepository", "firebaseSignUp: failure ${task.exception}")
                            false
                        }
                    }.await()

                if (isSuccess) {
                    emit(NetworkResult.Success(true))
                } else {
                    emit(NetworkResult.Error(message = "Oh, something went wrong"))
                }

            } catch (e: Exception) {
                emit(NetworkResult.Error(message = e.message))
            }

        }
    }

    override suspend fun firebaseSignIn(
        email: String,
        password: String
    ): Flow<NetworkResult<Boolean>> {

        return flow {

            var isSuccess = false

            emit(NetworkResult.Loading())

            try {

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        isSuccess = if (task.isSuccessful) {
                            Log.d("TAGRepository", "firebaseSignUp: success")
                            auth.currentUser != null
                        } else {
                            Log.d("TAGRepository", "firebaseSignUp: failure ${task.exception}")

                            false
                        }

                    }.await()

            } catch (e: Exception) {
                emit(NetworkResult.Error(message = e.message))
            }
        }
    }
}