package com.shoxrux.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.shoxrux.core.handler.NetworkResult
import com.shoxrux.data.utills.CollectionsConstants
import com.shoxrux.domain.model.post.PostModel
import com.shoxrux.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
    private val auth: FirebaseAuth
) : PostRepository {

    override suspend fun addPost(
        postModel: PostModel,
        byteArray: ByteArray
    ): Flow<NetworkResult<Boolean>> {
        return flow {
            emit(NetworkResult.Loading())

            try {

                val uploadTask = firebaseStorage.reference.child(CollectionsConstants.IMAGES)
                    .child(postModel.id.plus("_post_image.jpeg"))
                    .putBytes(byteArray)
                    .await()


                val downloadUrl = uploadTask.metadata?.reference?.downloadUrl?.await()

                if (downloadUrl != null) {

                    val updatedPost = postModel.copy(
                        imageUrl = downloadUrl.toString(),
                        authorId = auth.uid.toString()
                    )

                    firestore.collection(CollectionsConstants.POSTS)
                        .document(postModel.id)
                        .set(updatedPost)
                        .await()

                    emit(NetworkResult.Success(true))
                } else {
                    emit(NetworkResult.Error("Не удалось получить URL изображения"))
                }
            } catch (e: Exception) {
                emit(NetworkResult.Error(e.message ?: "Unknown error"))
            }
        }
    }
}
