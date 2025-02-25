package com.shoxrux.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.shoxrux.core.handler.NetworkResult
import com.shoxrux.data.utills.CollectionsConstants
import com.shoxrux.domain.model.post.PostModel
import com.shoxrux.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : PostRepository {

    override suspend fun addPost(postModel: PostModel): Flow<NetworkResult<Boolean>> {
        return flow {

            emit(NetworkResult.Loading())

            try {
                firestore.collection(CollectionsConstants.POSTS)
                    .document(postModel.id)
                    .set(postModel)
                    .await()

                emit(NetworkResult.Success(true))
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.message ?: "Unknown error"))
            }

        }
    }
}
