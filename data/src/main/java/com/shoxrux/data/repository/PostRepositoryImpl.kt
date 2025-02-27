package com.shoxrux.data.repository

import com.google.firebase.FirebaseException
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
    ): Flow<NetworkResult<Boolean>> = flow {
        emit(NetworkResult.Loading())

        try {
            val userId = auth.uid
            if (userId == null) {
                emit(NetworkResult.Error("Пользователь не авторизован"))
                return@flow
            }

            val imageRef = firebaseStorage.reference.child(CollectionsConstants.IMAGES)
                .child("${postModel.id}_post_image.jpeg")


            imageRef.putBytes(byteArray).await()


            val downloadUrl = imageRef.downloadUrl.await()


            val updatedPost = postModel.copy(
                imageUrl = downloadUrl.toString(),
                authorId = userId
            )

            firestore.collection(CollectionsConstants.POSTS)
                .document(postModel.id)
                .set(updatedPost)
                .await()

            emit(NetworkResult.Success(true))
        } catch (e: FirebaseException) {
            emit(NetworkResult.Error("Ошибка Firebase: ${e.message}"))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Unknown error"))
        }
    }

    override suspend fun getPosts(): Flow<NetworkResult<List<PostModel>>> = flow {
        emit(NetworkResult.Loading())

        try {
            val snapshot = firestore.collection(CollectionsConstants.POSTS).get().await()
            val posts = snapshot.documents.mapNotNull { it.toObject(PostModel::class.java) }
            emit(NetworkResult.Success(posts))
        } catch (e: FirebaseException) {
            emit(NetworkResult.Error("Ошибка Firebase: ${e.message}"))
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.message ?: "Unknown error"))
        }
    }
}
